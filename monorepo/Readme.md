# 모노레포 구조
Monolithic Repositories: 하나의 레포지토리에 여러 프로젝트 구성

```
monorepo
    apps/
        main-app
        shared-ui
        sub-app
    pnpm-workspace.yaml
```

sub-app 과 main-app 에서 공통으로 사용하는 컴포넌트를 하나로 관리 하고 싶음.

-> shared-ui 에서!
