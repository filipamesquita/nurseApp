---
name: commit-message
description: Generates a Conventional Commits-formatted commit message from a repo's staged git changes. Use this whenever the user asks for a commit message, wants help writing "a commit", says "write a commit message for this", "what should I commit this as", or has just finished a chunk of work and is about to commit. Also trigger if the user pastes a git diff and asks what to call it, or asks to summarize staged changes for a commit. Only produces the message text — never runs `git commit` itself unless the user explicitly asks it to.
---

# Commit Message Generator

Write a Conventional Commits message that accurately describes staged changes — not what the code could theoretically do, but what actually changed and why it matters to someone scanning `git log`.

## Workflow

1. **Get the staged diff.** Run `git diff --staged` (or `git diff --cached`) in the repo. If nothing is staged but there are unstaged changes, tell the user and ask whether to stage everything (`git add -A`) or generate the message from the unstaged diff instead — don't stage things silently.
2. **Check repo conventions.** Run `git log --oneline -20` to see if the project already uses Conventional Commits, a different scope style, or something else entirely. If the existing history clearly follows a different convention, follow that instead of the default below — matching the repo beats a generic standard.
3. **Identify the issue this commit belongs to.** Every commit in this repo must reference a GitHub issue. If the user hasn't said which issue, ask — don't guess. Look it up with `gh issue view <n>` if you need to confirm it exists.
4. **Read the diff like a reviewer, not a diffstat.** Don't just describe file names changed. Understand *why* the change happened: is it a new feature, a bug fix, a refactor with no behavior change, a docs update, a dependency bump? Look for the smallest coherent story that explains the diff — if the diff mixes unrelated changes, say so and suggest splitting into multiple commits (each against its own issue) rather than writing one message that awkwardly covers everything.
5. **Write the message** using the format below.
6. **Output the message only** — in a code block, ready to copy into `git commit -m`. Don't run the commit yourself unless the user explicitly says to (e.g., "commit it", "go ahead and commit"). If they do, run `git commit -m "<subject>" -m "<body>"`.

## Format

```
<type>(<scope>): <subject>

<body>

Refs #<issue-number>
```

- **type**: one of `feat`, `fix`, `refactor`, `docs`, `test`, `chore`, `style`, `perf`, `build`, `ci`. Pick the one that best matches the *primary* intent of the change.
- **scope** (optional): the module, package, or area affected (e.g., `domain`, `application`, `infrastructure`). Omit the parentheses entirely if no scope is obvious — don't force one.
- **subject**: imperative mood, lowercase, no trailing period, ideally under 50 characters. "add" not "added" or "adds".
- **body** (optional, only if it adds real information): wrap around 72 characters, explain *why* the change was made or any tradeoffs, not a restatement of the diff. Skip the body entirely for small, self-explanatory changes — an empty body is better than a padded one.
- **issue reference** (required, every commit): a trailer line `Refs #<issue-number>` linking the commit to the issue it advances. Use `Refs`, not `Closes`/`Fixes` — closing an issue is the PR's job (see `create-pull-request` skill), and one issue is typically satisfied by several commits.
- **breaking changes**: if the diff changes a public API or behavior in a backwards-incompatible way, add a `BREAKING CHANGE:` line in the body describing what breaks and why.

## Examples

**Diff**: adds a new `Periodicity` value object with due-date calculation.
```
feat(domain): add Periodicity value object

Self-validating interval with nextDueDateFrom(), the basis for
device replacement due-date detection.

Refs #6
```

**Diff**: renames a variable and removes a stray debug print, no behavior change.
```
chore(application): remove debug logging and rename unused var

Refs #7
```

**Diff**: fixes an off-by-one error in pagination.
```
fix(interfaces): correct off-by-one in page count calculation

Refs #12
```

## Notes

- If the diff is empty or you can't access git, say so plainly rather than guessing at a message.
- If the change is large or touches many unrelated areas, don't force a single tidy narrative — flag the mix and suggest splitting, since a commit message that papers over an incoherent diff isn't actually useful to future readers.
