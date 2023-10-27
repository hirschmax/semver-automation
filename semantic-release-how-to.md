# How to use semantic-release with in Maven project

In this document, we describe how we can use [semantic-release](https://github.com/semantic-release/semantic-release) 
for Maven projects in order to automate version management using [Semantic Versioning](https://semver.org/). 
By default, semantic-release uses [Angular Commit Message Conventions](https://github.com/angular/angular/blob/master/CONTRIBUTING.md#-commit-message-format)
that all developers should adhere to.

## dry-run mode

In order to be able to follow this tutorial, you need to have `node` installed on your local machine.

1. Create new Git repository with a new Maven project
2. Create a new file with the name `.releaserc` in the root of the Maven project
   ```yaml
   plugins:
     - "@semantic-release/commit-analyzer"
     - "@semantic-release/release-notes-generator"
     - - "@semantic-release/changelog"
       - changelogFile: docs/CHANGELOG.md
     - - "@semantic-release/git"
       - assets:
         - docs/CHANGELOG.md
     - - "@semantic-release/exec"
       - verifyReleaseCmd: 'mvn versions:set -DnewVersion="${nextRelease.version}" && echo "NEXT_VERSION=${nextRelease.version}" >> build.env'
     - -  "@semantic-release/git"
       - assets:
           - pom.xml
         message: "chore(release): bump ${nextRelease.version}"
   
   branches:
     - "main"
    ```
   A release step will run the following steps in this order, which can be implemented by one or more plugins:
   `verifyConditions`, `analyzeCommits` (required), `verifyRelease`, `generateNotes`, `prepare`, `publish`, `addChannel`,
   `success`, `fail`.
   If none of the plugins implements the `analyzeCommits` step, as a default the plugin `@semantic-release/commit-analyzer` is used.
   In the `.releaserc` file, we added the (official) [plugins](https://github.com/semantic-release/semantic-release/blob/master/docs/usage/plugins.md) `commit-analyzer`, `release-notes-generator`, `exec` and `git`.

   **[commit-analyzer](https://github.com/semantic-release/commit-analyzer)**

   This plugin is already part of semantic-release and doesn't have to be installed separately.

   **[release-notes-generator](https://github.com/semantic-release/release-notes-generator)**
   
   This plugin is already part of semantic-release and doesn't have to be installed separately.
   
   **[exec](https://github.com/semantic-release/exec)**
   
   This plugin is used to execute custom shell commands. In our example, we set the new version using the Maven CLI. 
   The value of the variable `nextRelease.version` is set by the commit-analyzer plugin.
   
   **[git](https://github.com/semantic-release/git)**
   
   Verifies, if access to the remote Git repository is granted and checks whether the assets and commit message are set.
   In our case, the only asset is the `pom.xml` since here the version is potentially updated and the commit message is 
   set using the `chore(release)` prefix 
   
3. Commit all changes and push them to the main branch (you could also set everything up for different and multiple 
   branches, you just need to update the `.releaserc` accordingly)
4. Install the used plugins locally\
   `npm install @semantic-release/changelog @semantic-release/git @semantic-release/exec`
5. Run semantic-release\
   `npx semantic-release`

After the first semantic-release run, the Maven project's version should have been set to `1.0.0` (if it was not already).
To test the automatic versioning, you can add some commits to the `main` branch following the 
[Angular Commit Message Conventions](https://github.com/angular/angular/blob/master/CONTRIBUTING.md#-commit-message-format)
and manually re-run the release steps

Since in our case the run is not triggered from a CI environment, it is only run in dry-run mode, so changes to the
`pom.xml` are not automatically committed or pushed to the remote repository.


## GitHub Actions

If you're hosting your maven project on GitHub, you can use GitHub Actions in order to trigger the releases from a CI environment.
Just make sure to enable actions and workflows on Settings/Actions/General and to give read and write permissions to the workflows.

Once this is done, you start with the steps for the *dry-run* and then, add a GitHub action workflow file to your repository, e.g.
`{project}/.github/workflows/semanticrelease.yml`:
```yaml
name: Java CI with Maven package and semantic-release

on:
  push:
    branches: [ "main" ]
  pull_request:
    branches: [ "main" ]

jobs:
  build:

    runs-on: ubuntu-latest

    steps:
    - uses: actions/checkout@v3
    - name: Set up JDK 17
      uses: actions/setup-java@v3
      with:
        java-version: '17'
        distribution: 'temurin'
        cache: maven
    - name: Build with Maven
      run: mvn -B package --file pom.xml
    - name: Setup Node.js
      uses: actions/setup-node@v3
      with:
        node-version: "lts/*"
    - name: Install plugins
      run: npm install @semantic-release/git @semantic-release/exec
    - name: Release
      env:
        GITHUB_TOKEN: ${{ secrets.GITHUB_TOKEN }}
      run: npx semantic-release
```

The important things to do for getting semantic-release running in any CI environment are having **Node** set up on the 
container and having a token with write-access available.


