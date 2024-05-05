
# 使用GraalVM 构建 tio-boot-native程序

介绍如何使用 TIO-Boot 和 GraalVM 设置项目。这对希望构建快速高效的 Java 服务器应用程序的开发人员特别有用。我们将从安装必要的依赖开始，然后构建一个可以独立于 JVM 运行的本地二进制镜像。

## 安装tio依赖项

我们需要从 TIO 生态系统克隆并构建几个项目。以下是逐步操作方法：

1. **克隆并构建 TIO 工具库**：
这些是 TIO 项目的辅助工具库。
```shell
git clone https://github.com/litongjava/tio-utils.git
cd tio-utils
mvn clean install -DskipTests -Dgpg.skip
```

2. **克隆并构建 TIO 核心库**：
这是所有基于 TIO 项目所需的核心库。
```shell
git clone https://github.com/litongjava/t-io.git
cd t-io
mvn clean install -DskipTests -Dgpg.skip
```
   
3. **克隆并构建 TIO HTTP 组件**：
这些组件为 TIO 应用程序启用 HTTP 功能。
```shell
git clone https://github.com/litongjava/tio-http.git
cd tio-http/tio-http-common/
mvn clean install -DskipTests -Dgpg.skip

cd ../tio-http-server
mvn clean install -DskipTests -Dgpg.skip
   ```

4. **克隆并构建 TIO Boot**：
TIO Boot 简化了 TIO 应用程序的引导过程。
```shell
git clone https://github.com/litongjava/tio-boot.git
cd tio-boot
mvn clean install -DskipTests -Dgpg.skip
```

## 安装 GraalVM

GraalVM 通过将代码提前编译成本地可执行文件来提高 Java 性能。

1. **下载并解压 GraalVM**：
这一步包括下载最新版本的 GraalVM 并将其解压到您偏好的目录。
```shell
wget https://download.oracle.com/graalvm/21/latest/graalvm-jdk-21_linux-x64_bin.tar.gz
mkdir -p ~/program/
tar -xf graalvm-jdk-21_linux-x64_bin.tar.gz -C ~/program/
```

2. **设置环境变量**：
更新您的系统环境变量以包括 GraalVM。这使得能够在全局使用 GraalVM 的 `java` 和其他命令行工具。
```shell
export JAVA_HOME=~/program/graalvm-jdk-21.0.3+7.1
export GRAALVM_HOME=~/program/graalvm-jdk-21.0.3+7.1
export PATH=$JAVA_HOME/bin:$PATH
```

## 安装 Maven

Apache Maven 是一个主要用于 Java 项目的构建自动化工具。

1. **下载并解压 Maven**：
与 GraalVM 类似，下载并设置 Maven 在您的本地环境。
```shell
wget https://dlcdn.apache.org/maven/maven-3/3.8.8/binaries/apache-maven-3.8.8-bin.zip
unzip apache-maven-3.8.8-bin.zip -d ~/program/
```

2. **设置环境变量**：
确保 Maven 的 `bin` 目录在您系统的 PATH 中，以便在任何地方使用 Maven 命令。
```shell
export MVN_HOME=~/program/apache-maven-3.8.8/
export PATH=$MVN_HOME/bin:$PATH
```

## 构建应用程序

现在所有工具和依赖都已设置好，让我们编译并运行一个示例应用程序。

1. **克隆示例应用程序**：
这是一个内置 HTTP 请求处理器的 TIO-Boot 示例应用程序。
```shell
git clone https://github.com/litongjava/tio-boot-http-request-handler-demo.git
```

2. **构建 JAR 文件（可选）**：
这一步将 Java 应用程序编译成 JAR 文件。如果您打算直接构建本地镜像，此步骤可选。
```shell
cd tio-boot-http-request-handler-demo
mvn clean package -DskipTests -Pproduction
```

3. **构建本地二进制镜像**：
最后，将应用程序编译成本地可执行文件。与在 JVM 上运行相比，这可以减少启动时间和资源消耗。
```shell
mvn clean package -DskipTests -Pnative
```

## 完整构建日志
```
Welcome to Ubuntu 20.04.6 LTS (GNU/Linux 5.15.0-105-generic x86_64)

 * Documentation:  https://help.ubuntu.com
 * Management:     https://landscape.canonical.com
 * Support:        https://ubuntu.com/advantage

 * Introducing Expanded Security Maintenance for Applications.
   Receive updates to over 25,000 software packages with your
   Ubuntu Pro subscription. Free for personal use.

     https://ubuntu.com/pro

Expanded Security Maintenance for Applications is not enabled.

80 updates can be applied immediately.
To see these additional updates run: apt list --upgradable

29 additional security updates can be applied with ESM Apps.
Learn more about enabling ESM Apps service at https://ubuntu.com/esm

New release '22.04.3 LTS' available.
Run 'do-release-upgrade' to upgrade to it.

Your Hardware Enablement Stack (HWE) is supported until April 2025.
Last login: Mon May  6 05:50:37 2024 from 192.168.3.8
root@ping-Inspiron-3458:~# export JAVA_HOME=~/program/graalvm-jdk-21.0.3+7.1
root@ping-Inspiron-3458:~# export GRAALVM_HOME=~/program/graalvm-jdk-21.0.3+7.1
root@ping-Inspiron-3458:~# export PATH=$JAVA_HOME/bin:$PATH
root@ping-Inspiron-3458:~# export MVN_HOME=~/program/apache-maven-3.8.8/
root@ping-Inspiron-3458:~# export PATH=$MVN_HOME/bin:$PATH
root@ping-Inspiron-3458:~# cd ~/code/tio-boot-http-request-handler-demo/
root@ping-Inspiron-3458:~/code/tio-boot-http-request-handler-demo# mvn clean package -DskipTests -Pnative
[INFO] Scanning for projects...
[INFO] 
[INFO] ---------< com.litongjava:tio-boot-http-request-handler-demo >----------
[INFO] Building tio-boot-http-request-handler-demo 1.0.0
[INFO]   from pom.xml
[INFO] --------------------------------[ jar ]---------------------------------
[INFO] 
[INFO] --- maven-clean-plugin:2.5:clean (default-clean) @ tio-boot-http-request-handler-demo ---
[INFO] Deleting /root/code/tio-boot-http-request-handler-demo/target
[INFO] 
[INFO] --- maven-resources-plugin:2.6:resources (default-resources) @ tio-boot-http-request-handler-demo ---
[INFO] Using 'UTF-8' encoding to copy filtered resources.
[INFO] skip non existing resourceDirectory /root/code/tio-boot-http-request-handler-demo/src/main/resources
[INFO] 
[INFO] --- maven-compiler-plugin:3.1:compile (default-compile) @ tio-boot-http-request-handler-demo ---
[INFO] Changes detected - recompiling the module!
[INFO] Compiling 4 source files to /root/code/tio-boot-http-request-handler-demo/target/classes
[INFO] 
[INFO] --- maven-resources-plugin:2.6:testResources (default-testResources) @ tio-boot-http-request-handler-demo ---
[INFO] Using 'UTF-8' encoding to copy filtered resources.
[INFO] skip non existing resourceDirectory /root/code/tio-boot-http-request-handler-demo/src/test/resources
[INFO] 
[INFO] --- maven-compiler-plugin:3.1:testCompile (default-testCompile) @ tio-boot-http-request-handler-demo ---
[INFO] No sources to compile
[INFO] 
[INFO] --- maven-surefire-plugin:2.12.4:test (default-test) @ tio-boot-http-request-handler-demo ---
[INFO] Tests are skipped.
[INFO] 
[INFO] --- maven-jar-plugin:2.4:jar (default-jar) @ tio-boot-http-request-handler-demo ---
[INFO] Building jar: /root/code/tio-boot-http-request-handler-demo/target/web-hello.jar
[INFO] 
[INFO] --- native-image-maven-plugin:21.2.0:native-image (default) @ tio-boot-http-request-handler-demo ---
[INFO] ImageClasspath Entry: com.litongjava:tio-boot:jar:1.4.3:compile (file:///root/.m2/repository/com/litongjava/tio-boot/1.4.3/tio-boot-1.4.3.jar)
[INFO] ImageClasspath Entry: com.litongjava:tio-utils:jar:3.7.3.v20240601-RELEASE:compile (file:///root/.m2/repository/com/litongjava/tio-utils/3.7.3.v20240601-RELEASE/tio-utils-3.7.3.v20240601-RELEASE.jar)
[INFO] ImageClasspath Entry: com.litongjava:tio-core:jar:3.7.3.v20240601-RELEASE:compile (file:///root/.m2/repository/com/litongjava/tio-core/3.7.3.v20240601-RELEASE/tio-core-3.7.3.v20240601-RELEASE.jar)
[INFO] ImageClasspath Entry: com.litongjava:tio-http-common:jar:3.7.3.v20240601-RELEASE:compile (file:///root/.m2/repository/com/litongjava/tio-http-common/3.7.3.v20240601-RELEASE/tio-http-common-3.7.3.v20240601-RELEASE.jar)
[INFO] ImageClasspath Entry: com.litongjava:tio-http-server:jar:3.7.3.v20240601-RELEASE:compile (file:///root/.m2/repository/com/litongjava/tio-http-server/3.7.3.v20240601-RELEASE/tio-http-server-3.7.3.v20240601-RELEASE.jar)
[INFO] ImageClasspath Entry: com.litongjava:tio-websocket-server:jar:3.7.3.v20240130-RELEASE:compile (file:///root/.m2/repository/com/litongjava/tio-websocket-server/3.7.3.v20240130-RELEASE/tio-websocket-server-3.7.3.v20240130-RELEASE.jar)
[INFO] ImageClasspath Entry: com.litongjava:tio-websocket-common:jar:3.7.3.v20240130-RELEASE:compile (file:///root/.m2/repository/com/litongjava/tio-websocket-common/3.7.3.v20240130-RELEASE/tio-websocket-common-3.7.3.v20240130-RELEASE.jar)
[INFO] ImageClasspath Entry: commons-io:commons-io:jar:2.10.0:compile (file:///root/.m2/repository/commons-io/commons-io/2.10.0/commons-io-2.10.0.jar)
[INFO] ImageClasspath Entry: com.thoughtworks.paranamer:paranamer:jar:2.8:compile (file:///root/.m2/repository/com/thoughtworks/paranamer/paranamer/2.8/paranamer-2.8.jar)
[INFO] ImageClasspath Entry: com.esotericsoftware:reflectasm:jar:1.11.9:compile (file:///root/.m2/repository/com/esotericsoftware/reflectasm/1.11.9/reflectasm-1.11.9.jar)
[INFO] ImageClasspath Entry: org.slf4j:slf4j-jdk14:jar:1.7.31:compile (file:///root/.m2/repository/org/slf4j/slf4j-jdk14/1.7.31/slf4j-jdk14-1.7.31.jar)
[INFO] ImageClasspath Entry: org.slf4j:slf4j-api:jar:1.7.31:compile (file:///root/.m2/repository/org/slf4j/slf4j-api/1.7.31/slf4j-api-1.7.31.jar)
[INFO] ImageClasspath Entry: com.litongjava:tio-boot-http-request-handler-demo:jar:1.0.0 (file:///root/code/tio-boot-http-request-handler-demo/target/web-hello.jar)
[WARNING] Major.Minor version mismatch between native-image-maven-plugin (21.2.0) and native-image executable (Unknown)
[INFO] Executing: /root/program/graalvm-jdk-21.0.3+7.1/lib/svm/bin/native-image -cp /root/.m2/repository/com/litongjava/tio-boot/1.4.3/tio-boot-1.4.3.jar:/root/.m2/repository/com/litongjava/tio-utils/3.7.3.v20240601-RELEASE/tio-utils-3.7.3.v20240601-RELEASE.jar:/root/.m2/repository/com/litongjava/tio-core/3.7.3.v20240601-RELEASE/tio-core-3.7.3.v20240601-RELEASE.jar:/root/.m2/repository/com/litongjava/tio-http-common/3.7.3.v20240601-RELEASE/tio-http-common-3.7.3.v20240601-RELEASE.jar:/root/.m2/repository/com/litongjava/tio-http-server/3.7.3.v20240601-RELEASE/tio-http-server-3.7.3.v20240601-RELEASE.jar:/root/.m2/repository/com/litongjava/tio-websocket-server/3.7.3.v20240130-RELEASE/tio-websocket-server-3.7.3.v20240130-RELEASE.jar:/root/.m2/repository/com/litongjava/tio-websocket-common/3.7.3.v20240130-RELEASE/tio-websocket-common-3.7.3.v20240130-RELEASE.jar:/root/.m2/repository/commons-io/commons-io/2.10.0/commons-io-2.10.0.jar:/root/.m2/repository/com/thoughtworks/paranamer/paranamer/2.8/paranamer-2.8.jar:/root/.m2/repository/com/esotericsoftware/reflectasm/1.11.9/reflectasm-1.11.9.jar:/root/.m2/repository/org/slf4j/slf4j-jdk14/1.7.31/slf4j-jdk14-1.7.31.jar:/root/.m2/repository/org/slf4j/slf4j-api/1.7.31/slf4j-api-1.7.31.jar:/root/code/tio-boot-http-request-handler-demo/target/web-hello.jar -H:+RemoveSaturatedTypeFlows --allow-incomplete-classpath --no-fallback -H:Class=com.litongjava.tio.web.hello.HelloApp -H:Name=web-hello
Warning: Using a deprecated option --allow-incomplete-classpath from command line. Allowing an incomplete classpath is now the default. Use --link-at-build-time to report linking errors at image build time for a class or package.
Warning: The option '-H:Name=web-hello' is experimental and must be enabled via '-H:+UnlockExperimentalVMOptions' in the future.
Warning: The option '-H:+RemoveSaturatedTypeFlows' is experimental and must be enabled via '-H:+UnlockExperimentalVMOptions' in the future.
Warning: Please re-evaluate whether any experimental option is required, and either remove or unlock it. The build output lists all active experimental options, including where they come from and possible alternatives. If you think an experimental option should be considered as stable, please file an issue.
========================================================================================================================
GraalVM Native Image: Generating 'web-hello' (executable)...
========================================================================================================================
[1/8] Initializing...                                                                                   (14.1s @ 0.07GB)
 Java version: 21.0.3+7-LTS, vendor version: Oracle GraalVM 21.0.3+7.1
 Graal compiler: optimization level: 2, target machine: x86-64-v3, PGO: ML-inferred
 C compiler: gcc (linux, x86_64, 9.4.0)
 Garbage collector: Serial GC (max heap size: 80% of RAM)
 1 user-specific feature(s):
 - com.oracle.svm.thirdparty.gson.GsonFeature
------------------------------------------------------------------------------------------------------------------------
 1 experimental option(s) unlocked:
 - '-H:Name' (alternative API option(s): -o web-hello; origin(s): command line)
------------------------------------------------------------------------------------------------------------------------
Build resources:
 - 5.80GB of memory (75.6% of 7.67GB system memory, determined at start)
 - 4 thread(s) (100.0% of 4 available processor(s), determined at start)
Found pending operations, continuing analysis.
[2/8] Performing analysis...  [******]                                                                 (117.5s @ 0.59GB)
    6,568 reachable types   (82.5% of    7,963 total)
    9,228 reachable fields  (55.0% of   16,776 total)
   33,806 reachable methods (56.6% of   59,757 total)
    2,120 types,   125 fields, and 1,715 methods registered for reflection
       60 types,    60 fields, and    55 methods registered for JNI access
        4 native libraries: dl, pthread, rt, z
[3/8] Building universe...                                                                              (15.0s @ 0.64GB)
[4/8] Parsing methods...      [******]                                                                  (44.8s @ 0.85GB)
[5/8] Inlining methods...     [***]                                                                      (7.2s @ 0.79GB)
[6/8] Compiling methods...    [****************]                                                       (261.5s @ 0.66GB)
[7/8] Layouting methods...    [[7/8] Layouting methods...    [***]                                                                      (9.7s @ 0.75GB)
[8/8] Creating image...       [[8/8] Creating image...       [***]                                                                     (10.6s @ 0.87GB)
  17.48MB (52.87%) for code area:    18,874 compilation units
  14.35MB (43.41%) for image heap:  205,637 objects and 49 resources
   1.23MB ( 3.72%) for other data
  33.06MB in total
------------------------------------------------------------------------------------------------------------------------
Top 10 origins of code area:                                Top 10 object types in image heap:
  11.82MB java.base                                            4.68MB byte[] for code metadata
   2.59MB svm.jar (Native Image)                               2.48MB byte[] for java.lang.String
 392.55kB reflectasm-1.11.9.jar                                1.41MB java.lang.String
 341.89kB java.rmi                                             1.11MB java.lang.Class
 265.50kB java.naming                                        644.48kB byte[] for general heap data
 258.62kB tio-core-3.7.3.v20240601-RELEASE.jar               552.52kB heap alignment
 251.39kB jdk.crypto.ec                                      369.42kB byte[] for reflection metadata
 192.82kB java.desktop                                       307.88kB com.oracle.svm.core.hub.DynamicHubCompanion
 154.33kB tio-http-common-3.7.3.v20240601-RELEASE.jar        253.31kB java.util.HashMap$Node
 148.94kB java.logging                                       191.96kB char[]
1002.92kB for 25 more packages                                 2.41MB for 1718 more object types
                              Use '-H:+BuildReport' to create a report with more details.
------------------------------------------------------------------------------------------------------------------------
Security report:
 - Binary includes Java deserialization.
 - Use '--enable-sbom' to embed a Software Bill of Materials (SBOM) in the binary.
------------------------------------------------------------------------------------------------------------------------
Recommendations:
 G1GC: Use the G1 GC ('--gc=G1') for improved latency and throughput.
 PGO:  Use Profile-Guided Optimizations ('--pgo') for improved throughput.
 INIT: Adopt '--strict-image-heap' to prepare for the next GraalVM release.
 HEAP: Set max heap for improved and more predictable memory usage.
 CPU:  Enable more CPU features with '-march=native' for improved performance.
------------------------------------------------------------------------------------------------------------------------
                       43.0s (8.9% of total time) in 600 GCs | Peak RSS: 1.73GB | CPU load: 2.34
------------------------------------------------------------------------------------------------------------------------
Produced artifacts:
 /root/code/tio-boot-http-request-handler-demo/target/web-hello (executable)
========================================================================================================================
Finished generating 'web-hello' in 8m 2s.
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  08:10 min
[INFO] Finished at: 2024-05-06T06:01:02+08:00
[INFO] ------------------------------------------------------------------------
```
## 启动测试
```
$ ./target/web-hello 
May 06, 2024 6:04:15 AM com.litongjava.tio.boot.context.TioApplicationContext run
INFO: scan class and init:-1714946655150(ms),config:0(ms),server:2(ms),http route:0(ms)
May 06, 2024 6:04:15 AM com.litongjava.tio.boot.context.TioApplicationContext printUrl
INFO: port:80
http://localhost
6ms
```

```shell
curl http://localhost/hi
curl http://localhost/hello
```

构建后的文件大约是34M
[下载地址](https://github.com/litongjava/tio-boot-http-request-handler-demo/releases/download/v1.0.0/web-hello)
