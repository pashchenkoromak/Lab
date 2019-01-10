echo "Build 'commons' package"
javac -d ./build ./commons/*.java && \
    echo "'commons' package is built" || exit 1;

echo ""

echo "build 'db' package"
javac -d ./build -cp build/: ./db/*.java && \
    echo "'db' package is built" || exit 1;

echo ""

echo "build Main"
javac -d ./build -cp build/: ./Main.java

echo ""

echo "Run Main"
cd build && java Main