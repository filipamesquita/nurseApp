# US004 — Secret Scanning with Gitleaks

*As a developer, I want Gitleaks running on every push, so that secrets and credentials are never committed to the repository.*

## Acceptance Criteria

- Gitleaks runs as a blocking step in the CI pipeline
- Pipeline fails if any secret is detected
- A `.gitleaks.toml` config file is present for any project-specific rules or allowlisted false positives
- Runs on both pushes and pull requests
