echo "Build 'commons' package"
javac -d ./build ./commons/*.java && \
    echo "'commons' package is built" || exit 1;

echo ""

echo "build 'db' package"
javac -d ./build -cp build/: ./db/*.java && \
    echo "'db' package is built" || exit 1;

echo ""

echo "Build tests"
javac -d ./build/ -cp ./build:/usr/share/java/junit4.jar ./tests/*.java && \
    echo "'tests' package is built" || exit 1;

echo ""

echo "Run tests"
cd ./build &&
    java -cp /usr/share/java/junit4.jar:./ tests.TestRunner || exit 1
echo "Success"
