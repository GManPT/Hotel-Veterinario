#!/bin/bash

# Diretório onde está o hva.app.App (ajuste se necessário)
test_dir="auto-tests"
expected_dir="${test_dir}/expected"

# Cores para a saída do terminal
GREEN='\033[0;32m'
RED='\033[0;31m'
NC='\033[0m' # Sem cor (reseta para a cor padrão)

GROUP="$1"

cd "$GROUP" || { echo "Erro: Grupo $GROUP não encontrado."; exit 1; }
make

total_tests=0
passed_tests=0

# Loop para iterar de 01 até 24
# Define os limites de XX e YY
for i in $(seq -w 1 50); do        # Para XX (o primeiro número)
    for j in $(seq -w 1 50); do    # Para YY (o segundo número)
        
        # Define o prefixo dos arquivos com base nos valores de i e j
        prefix="A-${i}-${j}-M-ok"
        
        # Criação dos caminhos dos arquivos
        import_file="${test_dir}/${prefix}.import"
        in_file="${test_dir}/${prefix}.in"
        out_file="${expected_dir}/${prefix}.out"

        # Verifica se os arquivos .in e .out existem
        if [[ ! -f "$in_file" ]]; then
            continue
        fi
        if [[ ! -f "$out_file" ]]; then
            continue
        fi

        total_tests=$((total_tests + 1))

        # Executa o programa com os arquivos de teste e verifica se o arquivo import existe
        if [[ ! -f "$import_file" ]]; then
            java -Din=${test_dir}/${prefix}.in -Dout=test.outhyp hva.app.App
        else
            java -Dimport=${test_dir}/${prefix}.import -Din=${test_dir}/${prefix}.in -Dout=test.outhyp hva.app.App
        fi

        # Compara a saída gerada com o arquivo de referência
        diff -b ${expected_dir}/${prefix}.out test.outhyp

        # Mostra uma mensagem de sucesso ou insucesso
        if [ $? -eq 0 ]; then
            echo -e "${GREEN}Test ${i}-${j}: Success - No differences${NC}"
            passed_tests=$((passed_tests + 1))
        else
            echo -e "${RED}Test ${i}-${j}: Failure - Differences found${NC}"
        fi
    done
done

success_rate=$((100 * passed_tests / total_tests))

# Mostra o resumo final
echo -e "${GREEN}\nTotal Tests: $total_tests"
echo -e "${GREEN}With Success: $passed_tests"
echo -e "${NC}Percentage of Success: $success_rate%"

rm -f *.dat
