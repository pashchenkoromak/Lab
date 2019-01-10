echo "Build 'commons' package"
javac -d ./build ./commons/*.java && \
    echo "'commons' package is built" || exit 1;

echo ""

echo "build 'db' package"
javac -d ./build -cp build/: ./db/*.java && \
    echo "'db' package is built" || exit 1;

echo ""

echo "build Test"
javac -d ./build -cp build/: ./Test.java

echo ""

echo "Run Test"
cd build && java Test