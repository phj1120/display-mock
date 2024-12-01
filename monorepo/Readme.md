# 모노레포 구조 + Nuxt Layer

Monolithic Repositories: 하나의 레포지토리에 여러 프로젝트 구성

```
monorepo/
    apps/
        main-app/
            package.json
        shared-ui/
        sub-app/
            package.json
    pnpm-workspace.yaml
    package.json
```

sub-app 과 main-app 에서 공통으로 사용하는 컴포넌트를 하나로 관리 하고 싶음.

-> shared-ui 에서!

* pnpm-workspace.yaml 에서 packages 경로 지정
* main-app, sub-app 의 nuxt.config.js 에 extends 추가
* main-app, sub-app 의 package.json 에 shared-ui 의 dependency 추가 < 버전 관리 하고 싶으면 넥서스 같은 곳에 올려야할듯?
* main-app 실행하고 싶을 경우
    ```
    pnpm main dev
    ```
  main > monorepo/package.json ()

  dev > monorepo/apps/main-app/package.json

  package.json 에 지정한 대로 실행 됨.

### 참고
* https://dev.to/leamsigc/nuxt-3-monorepo-example-basic-example-1d61
* https://nuxt.com/docs/api/nuxt-config#extends
* https://pnpm.io/workspaces
* https://nuxt.com/docs/getting-started/layers

