# Java-programs (Gradle)

This project was migrated from Maven to Gradle.

- Gradle Wrapper: 8.10.2
- Java Toolchain: 17
- Encoding: UTF-8

Build (skip tests):

```bash
./gradlew clean build -x test
```

Build (with tests):

```bash
./gradlew clean build
```

Assemble JAR only:

```bash
./gradlew assemble
```

Artifacts will be under `build/libs/`.

If you see an error like `Unsupported class file major version 68` it means your system Java is too new for this Gradle runtime. On macOS, point Gradle to JDK 17 or 21:

```bash
export JAVA_HOME=$(/usr/libexec/java_home -v 17)
export PATH="$JAVA_HOME/bin:$PATH"
./gradlew --version
./gradlew clean build
```

Alternatively:

```bash
./gradlew -Dorg.gradle.java.home=$(/usr/libexec/java_home -v 17) clean build
```

Gradle will auto-download the JDK for toolchains, but the Gradle runtime still needs a supported JDK to start.

Notes:
- `pom.xml` has been removed.
- `target/` from the previous Maven build is still present; you can delete it safely:

```bash
rm -rf target/
```

