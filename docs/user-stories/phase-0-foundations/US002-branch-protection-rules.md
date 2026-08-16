# US002 — Branch Protection Rules

*As a developer, I want branch protection rules on `main`, so that no code is merged without going through the CI pipeline.*

## Acceptance Criteria

- Direct push to `main` is blocked
- PRs require at least 1 approval before merging
- PRs require all CI checks to pass before merging
- `CODEOWNERS` file is defined

## Implementation notes

Configured via GitHub's **Rulesets** (Settings → Rules), not the older "classic" branch
protection UI — GitHub has moved to rulesets as the current mechanism; the concepts map
1:1 (a ruleset targeting `main` is equivalent to a classic branch protection rule).

- **Ruleset**: `main-protection`, target = default branch (`main`), enforcement = Active.
- **Bypass list**: `Repository admin` role, mode `Always allow`. This is the deliberate fix
  for a real constraint: GitHub never lets a PR's author approve their own PR, regardless of
  any reviewer/approver persona (see `docs/product-owner.md`'s framing of Filipa Mesquita as
  sole approver). Without an admin bypass, the required-approval rule would lock the sole
  developer out of merging her own PRs. The rule itself stays fully enforced for any future
  external collaborator; the repo owner can bypass it as admin.
- **Required PR approvals**: 1, with **Require review from Code Owners** enabled (uses
  `.github/CODEOWNERS` — Filipa Mesquita on `*`).
- **Required status checks** (must match the job `name:` fields in
  `.github/workflows/ci.yml` exactly): `Secret Scanning (Gitleaks)`, `SAST (Semgrep)`,
  `Build and Test with Coverage`, `Software Composition Analysis`.
- **Block force pushes** and **Restrict deletions** on `main`: both enabled.
