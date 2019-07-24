# Gitlab CI

Advantages:

- Never forget to test
- Will not broke existing functions
- Combine with webhook (Slack) / email to get nitification easily

## Set Up

1. Test Cases 
2. Set Up CI/CD from project repo site ( `.gitlab-ci.yml` )
3. Runners

## Runners

> Settings -> CI/CD -> Runners

- Specific Runners
  - <https://docs.gitlab.com/runner/install/linux-manually.html>
- Set up a specific Runner manually
  - Install GitLab Runner <https://docs.gitlab.com/runner/install/>
- Shared Runners
  - gitrunner.oa.style.me
- Group Runners
  
## Pipeline Badges

[![pipeline status](https://gitlab.com/wesely.ong/acgwidgets/badges/master/pipeline.svg)](https://gitlab.com/wesely.ong/acgwidgets/commits/master)

[![coverage report](https://gitlab.com/wesely.ong/acgwidgets/badges/master/coverage.svg)](https://gitlab.com/wesely.ong/acgwidgets/commits/master)

## Triggers

> Settings -> CI/CD -> Triggers

Use REST to invoke pipeline

```curl
curl -X POST \
     -F token=TOKEN \
     -F ref=REF_NAME \
     https://gitlab.com/api/v4/projects/12558569/trigger/pipeline
```

- `request URL` : can be found in the settings.
- `token` : use `Add Trigger` in the settings to get a token
- `ref` : `master` / `develop` / `...`
