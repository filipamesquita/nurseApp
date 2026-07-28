# US003 — CI Pipeline Skeleton

*As a developer, I want a GitHub Actions pipeline skeleton in place, so that all future security and build stages have a consistent structure to plug into.*

## Acceptance Criteria

- Pipeline triggers on every push and pull request
- Pipeline has clearly named jobs (e.g. `secret-scan`, `sast`, `build`)
- Placeholder jobs pass successfully on first run
- Pipeline file lives at `.github/workflows/ci.yml`
