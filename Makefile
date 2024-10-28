all:
	(cd po-uilib; make $(MFLAGS) all)
	(cd hva-core; make $(MFLAGS) all)
	(cd hva-app; make $(MFLAGS) all)

clean:
	(cd po-uilib; make $(MFLAGS) clean)
	(cd hva-core; make $(MFLAGS) clean)
	(cd hva-app; make $(MFLAGS) clean)

install:
	(cd po-uilib; make $(MFLAGS) install)
	(cd hva-core; make $(MFLAGS) install)
	(cd hva-app; make $(MFLAGS) install)

