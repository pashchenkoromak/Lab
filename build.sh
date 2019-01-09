echo "Build 'commons' package"
javac -d ./build ./commons/*.java && \
    echo "'commons' package is built" || exit 1;

echo ""

echo "build 'db' package"
javac -d ./build -cp build/:/usr/lib/jvm/java-8-openjdk-amd64/jre/lib/ext/jfxrt.jar ./db/*.java && \
    echo "'db' package is built" || exit 1;