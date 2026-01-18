## 目标
- 仅在 main、dev 分支推送以及任何 tag 推送时触发。
- 镜像名保持不变：registry.cn-chengdu.aliyuncs.com/doomer/notiflow。
- 镜像 tag 仅使用分支名或 tag 原始内容（不再追加 -jar/-native 等后缀）。

## 触发与环境
- on.push.branches: [main, dev]
- on.push.tags: ['*']（任何 tag）
- 保留现有 env：REGISTRY/REPOSITORY/IMAGE_NAME。

## 构建类型
- 提供两个 Job：
  - JVM 镜像：使用 Quarkus 官方 Dockerfile.jvm。
  - Native 镜像：使用 Dockerfile.native（项目已提供）。
- 两个 Job 均在 main/dev/tag 推送下触发；镜像 tag 使用 github.ref_name（自动是分支名或 tag 名）。

## 关键改造点
- 移除对 build.gradle 的版本解析；改为 Maven 项目流程：
  - 使用 actions/setup-java 安装 JDK 21（或与 pom.xml release 对应）。
  - 运行 mvn -DskipTests clean package 以产出 target/quarkus-app。
- 修正 Dockerfile 路径：
  - JVM：src/main/docker/Dockerfile.jvm
  - Native：src/main/docker/Dockerfile.native
- 登录镜像仓库：沿用 docker/login-action，并使用现有 secrets。
- 生成镜像 tag：
  - TAG="$REGISTRY/$REPOSITORY/$IMAGE_NAME:${{ github.ref_name }}"
  - 只推送该单一 tag。
- 保留 Buildx 与缓存设置；平台为 linux/amd64。

## 具体 Job 步骤
### build-jvm
- checkout
- setup-java（temurin，java-version: 21）
- setup-buildx
- login（使用现有 secrets）
- mvn -DskipTests clean package
- 生成 TAG（使用 github.ref_name）
- docker/build-push-action：context .，file src/main/docker/Dockerfile.jvm，tags 为上一步生成的 TAG，platforms linux/amd64，开启 gha 缓存。

### build-native
- checkout
- setup-buildx
- login（使用现有 secrets）
- 生成 TAG（使用 github.ref_name）
- docker/build-push-action：context .，file src/main/docker/Dockerfile.native，tags 为上一步生成的 TAG，platforms linux/amd64，开启 gha 缓存。

## 备注
- 如果后续只需构建其中一种镜像（JVM 或 Native），可以删掉另一 Job 即可。
- 若需要限制 tag 触发范围（如仅 v*），可将 on.push.tags 改为 ['v*']。

## 交付
- 我将按照上述方案直接改写 .github/workflows/workflow.yml，移除 Gradle 相关步骤，改为 Maven 构建，并修正 Dockerfile 路径与 tag 生成逻辑。请确认。