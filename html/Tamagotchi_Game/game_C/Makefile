CC = emcc
CFLAGS = -O3
FONC ?= _main

# commande pour lancé la conversion du .c en .js et .wasm : make "code.js" FONC="'_fonc1', '_fonc2'"
%.js: %.c
	$(CC) $< -o ./js_file/$@ $(CFLAGS) \
	-s EXPORTED_FUNCTIONS="[$(FONC)]" \
	-s EXPORTED_RUNTIME_METHODS="['ccall','cwrap']"