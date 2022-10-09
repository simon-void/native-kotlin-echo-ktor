# native-kotlin-echo-ktor

a minimal echo server (invoke http://localhost:8080/echo/any_msg ) implemented in Kotlin native with Ktor.

Following this documentation https://ktor.io/docs/native-server.html

## How to compile to native

```
./gradlew build
```
This only works on Linux and macOS. Windows isn't supported.

You should now be able to execute the native binary:
```
./build/bin/native/releaseExecutable/native-kotlin-echo-ktor.kexe
```
