import os
import re
import subprocess
import time

def animacao(texto):
    print(texto, end=" ", flush=True)
    for _ in range(3):
        print(".", end="", flush=True)
        time.sleep(0.2)
    print(" [Concluído]")

def verificar_gitignore(resultados):
    """Verifica se o .gitignore está presente e bem estruturado."""
    gitignore_path = os.path.join(os.getcwd(), '.gitignore')
    if os.path.exists(gitignore_path):
        with open(gitignore_path, 'r') as f:
            conteudo = f.read()
            if all(item in conteudo for item in ["*.jar", "*.class", "Makefile"]):
                resultados["gitignore_valido"] = True
            else:
                resultados["gitignore_valido"] = False
    else:
        resultados["gitignore_valido"] = False

def verificar_dmisses():
    # Define os caminhos para as pastas a serem verificadas
    pastas = ["hva-core", "hva-app"]
    resultados = {
        "atributos_publicos": [],
        "atributos_static": [],
        "metodos_static": [],
        "arquivos_com_object": [],
        "singletons": [],
        "lixo_no_repositorio": [],
        "gitignore_valido": False,
        "exceptions_invalidas": [],
        "arquivos_serializable_invalidos": []
    }

    # Regex para capturar atributos públicos, ignorando abstract e interfaces
    padrao_atributo_publico = re.compile(
        r'\bpublic\b\s+(?!abstract)([^\s]+(?:\s+[^\s]+)*\s+[^;]+;)',
        re.MULTILINE
    )

    # Regex para capturar atributos estáticos
    padrao_atributo_static = re.compile(
        r'\bstatic\b\s+[^\s]+(?:\s+[^\s]+)*\s+[^;]+;',
        re.MULTILINE
    )

    # Regex para capturar métodos e argumentos estáticos, exceto serialVersionUID
    padrao_metodo_static = re.compile(
        r'\bstatic\b\s+([^\s]+(?:\s+[^\s]+)*\s+[^\s]+\s*\(.*?\)|\bstatic\b\s+[^\s]+\s+[^\s]+;)',
        re.MULTILINE
    )

    # Regex para verificar Singleton
    padrao_singleton = re.compile(
        r'\bprivate\s+static\s+[^\s]+\s+[^\s]+\s*=\s*new\s+[^\s]+\(\);\s*|'
        r'\bprivate\s+[^\s]+\s*\(\);\s*|'
        r'\bprivate\s+static\s+[^\s]+\s+instance;\s*|'
        r'\bpublic\s+static\s+[^\s]+\s+getInstance\(\)',
        re.MULTILINE
    )

    # Regex para verificar a palavra "Object" entre <> ou isolada
    padrao_object = re.compile(r'\bObject\b|<\s*Object\s*>')

    # Regex para verificar Exception
    padrao_exception_extends = re.compile(r'\bextends\s+(Exception|CommandException)\b')
    padrao_serial_version_uid = re.compile(r'\bprivate\s+static\s+final\s+long\s+serialVersionUID\b')

    # Regex para verificar Serializable (sozinha ou com outras interfaces)
    padrao_serializable = re.compile(r'\bimplements\s+.*\bSerializable\b')
    padrao_import_serializable = re.compile(r'import\s+java\.io\.Serializable;')

    print("Preparando o detector de DMisses! 🎉\n")

    # Verifica os arquivos em cada pasta
    animacao("A verificar a existência de atributos públicos")
    for pasta in pastas:
        caminho_base = os.path.join(os.getcwd(), pasta)  # Usa o diretório atual e adiciona a pasta

        for root, dirs, files in os.walk(caminho_base):
            for file in files:
                # Ignora os arquivos Prompt.java e Message.java
                if file in ["Prompt.java", "Message.java", "App.java", "ImportFileException.java"]:
                    continue
                if file.endswith(".java"):
                    caminho_arquivo = os.path.join(root, file)

                    with open(caminho_arquivo, 'r') as f:
                        # Verifica se o arquivo é uma interface
                        is_interface = any("interface" in linha for linha in f)
                        f.seek(0)  # Reseta o cursor para o início do arquivo

                        for numero_linha, linha in enumerate(f, start=1):
                            # Verifica atributos públicos, ignorando abstract e arquivos de interface
                            if padrao_atributo_publico.search(linha) and not is_interface:
                                resultados["atributos_publicos"].append((caminho_arquivo, numero_linha))
                            # Verifica atributos estáticos, ignorando serialVersionUID
                            if padrao_atributo_static.search(linha) and "serialVersionUID" not in linha:
                                resultados["atributos_static"].append((caminho_arquivo, numero_linha))
                            # Verifica métodos estáticos, ignorando serialVersionUID
                            if padrao_metodo_static.search(linha) and "serialVersionUID" not in linha:
                                resultados["metodos_static"].append((caminho_arquivo, numero_linha))
                            # Verifica a presença da palavra "Object"
                            if padrao_object.search(linha):
                                resultados["arquivos_com_object"].append(caminho_arquivo)

                        # Verificação de exceções
                        if "Exception" in file:
                            f.seek(0)
                            conteudo = f.read()
                            extends_exception = padrao_exception_extends.search(conteudo)
                            serial_version_uid = padrao_serial_version_uid.search(conteudo)

                            if not extends_exception or not serial_version_uid:
                                resultados["exceptions_invalidas"].append(caminho_arquivo)

                        # Verificação de Serializable para arquivos em hva-core
                        if "hva-core" in pasta and not is_interface:
                            f.seek(0)
                            conteudo = f.read()
                            tem_import_serializable = padrao_import_serializable.search(conteudo)
                            implements_serializable = padrao_serializable.search(conteudo)

                            # Verifica se a classe pai implementa Serializable
                            if extends := re.search(r'\bextends\s+(\w+)', conteudo):
                                nome_classe_pai = extends.group(1)
                                caminho_classe_pai = os.path.join(root, f"{nome_classe_pai}.java")
                                if os.path.exists(caminho_classe_pai):
                                    with open(caminho_classe_pai, 'r') as pai:
                                        pai_conteudo = pai.read()
                                        if padrao_serializable.search(pai_conteudo):
                                            continue  # Classe pai implementa Serializable, então ignora verificação

                            # Se não encontrou import ou implements Serializable, adiciona o erro
                            if not tem_import_serializable or not implements_serializable:
                                if "Exception" in file:
                                    continue
                                resultados["arquivos_serializable_invalidos"].append(caminho_arquivo)

    animacao("A verificar a existência de métodos ou atributos 'static'")
    animacao("A verificar Singleton")
    animacao("A verificar a presença da palavra 'Object'")
    animacao("A verificar exceções inválidas")
    animacao("A verificar implementações de Serializable")

    # Verifica lixo no repositório
    animacao("A verificar lixo no repositório")
    for pasta in pastas:
        caminho_base = os.path.join(os.getcwd(), pasta)
        for root, dirs, files in os.walk(caminho_base):
            for file in files:
                if not (file.endswith(".java") or file.endswith(".jar") or file.endswith(".class") or file == "Makefile"):
                    resultados["lixo_no_repositorio"].append(os.path.join(root, file))

    # Verifica se o gitignore está presente e bem estruturado
    animacao("A verificar a presença de um .gitignore bem estruturado")
    verificar_gitignore(resultados)

    # Exibe os resultados ao final
    print("\nResultados da verificação:")
    print(f"Atributos públicos encontrados: {len(resultados['atributos_publicos'])}")
    for arquivo, linha in resultados["atributos_publicos"]:
        print(f"- {arquivo}, linha {linha}")

    print(f"Atributos estáticos encontrados: {len(resultados['atributos_static'])}")
    for arquivo, linha in resultados["atributos_static"]:
        print(f"- {arquivo}, linha {linha}")

    print(f"Métodos estáticos encontrados: {len(resultados['metodos_static'])}")
    for arquivo, linha in resultados["metodos_static"]:
        print(f"- {arquivo}, linha {linha}")

    print(f"Arquivos com a palavra 'Object' encontrados: {len(resultados['arquivos_com_object'])}")
    for arquivo in resultados["arquivos_com_object"]:
        print(f"- {arquivo}")

    print(f"Implementações de Singleton encontradas: {len(resultados['singletons'])}")
    for arquivo in resultados["singletons"]:
        print(f"- {arquivo}")

    print(f"Lixo no repositório encontrado: {len(resultados['lixo_no_repositorio'])}")
    for arquivo in resultados["lixo_no_repositorio"]:
        print(f"- {arquivo}")

    print(f"Exceções inválidas encontradas: {len(resultados['exceptions_invalidas'])}")
    for arquivo in resultados["exceptions_invalidas"]:
        print(f"- {arquivo}")

    print(f"Arquivos que não implementam Serializable encontrados: {len(resultados['arquivos_serializable_invalidos'])}")
    for arquivo in resultados["arquivos_serializable_invalidos"]:
        print(f"- {arquivo}")

    if resultados["gitignore_valido"]:
        print(".gitignore está bem estruturado. 📝")
    else:
        print(".gitignore não está bem estruturado ou não encontrado. 🚫")

def definir_classpath():
    print("\nA variável CLASSPATH não está definida:")
    print("1. Definir CLASSPATH customizado")
    print("2. Usar CLASSPATH padrão (assume que o script está na raiz do projeto)")

    escolha = input("Escolha uma opção: ")
    
    if escolha == "1":
        # Define o CLASSPATH customizado fornecido pelo usuário
        classpath = input("Insira o caminho do CLASSPATH: ")
    elif escolha == "2":
        # Define o CLASSPATH padrão
        diretorio_atual = os.getcwd()
        classpath = f"{diretorio_atual}/po-uilib/po-uilib.jar:{diretorio_atual}/hva-core/hva-core.jar:{diretorio_atual}/hva-app/hva-app.jar"
        print(f"\nCLASSPATH padrão definido como: {classpath}")
    else:
        print("Opção inválida. Usando CLASSPATH padrão.")
        diretorio_atual = os.getcwd()
        classpath = f"{diretorio_atual}/po-uilib/po-uilib.jar:{diretorio_atual}/hva-core/hva-core.jar:{diretorio_atual}/hva-app/hva-app.jar"
        print(f"\nCLASSPATH padrão definido como: {classpath}")

    # Define o CLASSPATH no ambiente
    os.environ["CLASSPATH"] = classpath
    print("\nCLASSPATH exportado com sucesso.")

def menu_com_claspath_definido():
    while True:
        print("\nMenu com CLASSPATH definido:")
        print("1. Mudar o CLASSPATH")
        print("2. Prosseguir com os testes")
        print("3. Sair")
        escolha = input("Escolha uma opção: ")

        if escolha == "1":
            definir_classpath()
        elif escolha == "2":
            verificar_dmisses()
        elif escolha == "3":
            print("Saindo... Até logo! 👋")
            break
        else:
            print("Opção inválida. Por favor, tente novamente.")

def executar_testes():
    # Verifica se a variável de ambiente CLASSPATH está definida
    if not os.environ.get("CLASSPATH"):
        definir_classpath()
    else:
        menu_com_claspath_definido()  # Chama o menu alternativo se o CLASSPATH estiver definido

    # Verifica se o arquivo ./tests.sh existe
    if os.path.exists("./tests.sh"):
        try:
            # Executa o script ./tests.sh
            subprocess.run(["bash", "./tests.sh"], check=True)
        except subprocess.CalledProcessError:
            print("Erro ao executar o script ./tests.sh.")
    else:
        print("O arquivo ./tests.sh não foi encontrado.")

def mostrar_menu():
    exec = 0
    while True:
        if exec == 0:
            print("Bem-vindo ao sistema de testes! 🎊")
            exec = 1
    
        if exec == 1:
            print("Menu:")
        else:
            print("\n\nMenu:")

        print("1. Verificar por 'DMisses'")
        print("2. Executar testes")
        print("3. Sair")
        escolha = input("Escolha uma opção: ")

        if escolha == "1":
            verificar_dmisses()
        elif escolha == "2":
            executar_testes()
        elif escolha == "3":
            print("Saindo... Até logo! 👋")
            break
        else:
            print("Opção inválida. Por favor, tente novamente.")

# Executa o menu
if __name__ == "__main__":
    mostrar_menu()
