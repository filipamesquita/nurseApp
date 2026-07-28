# US002 — Branch Protection Rules

*As a developer, I want branch protection rules on `main`, so that no code is merged without going through the CI pipeline.*

## Acceptance Criteria

- Direct push to `main` is blocked
- PRs require at least 1 approval before merging
- PRs require all CI checks to pass before merging
- `CODEOWNERS` file is defined
