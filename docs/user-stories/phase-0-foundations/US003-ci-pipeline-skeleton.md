# US003 — CI Pipeline Skeleton

*As a developer, I want a GitHub Actions pipeline skeleton in place, so that all future security and build stages have a consistent structure to plug into.*

## Acceptance Criteria

- Pipeline triggers on every pull request (not every push — running the full pipeline,
  including Gitleaks and the build, on every push to any branch would be unnecessarily
  heavy; `pull_request` already covers every commit that matters before merge)
- Pipeline has clearly named jobs (`secret-scan`, `sast-semgrep`, `build-and-test-with-coverage`,
  `sca`)
- Placeholder jobs pass successfully on first run; `secret-scan` (Gitleaks) and
  `build-and-test-with-coverage` (`mvn verify`) are real from the start, `sast-semgrep` and
  `sca` remain placeholders until their own issues land
- Pipeline file lives at `.github/workflows/ci.yml`
