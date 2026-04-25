# kids-study-app

子ども向け学習アプリ。

## デプロイ先

- GitHub リポジトリ: https://github.com/kasa555302/kids-study-app.git

## 技術スタック

> プロジェクト初期化後に確定し、このセクションを更新してください。

| 用途 | 候補 / 確定技術 |
|------|----------------|
| フロントエンド | React (Vite) or Next.js |
| スタイリング | Tailwind CSS |
| 言語 | TypeScript |
| テスト | Vitest / React Testing Library |
| パッケージマネージャ | npm |

## Git 運用ルール

- コードを変更するたびに、変更内容を `git add` → `git commit` → `git push` の順で必ず GitHub にプッシュする
- リモートは `origin`（https://github.com/kasa555302/kids-study-app.git）
- コミットメッセージは変更内容を日本語で簡潔に記述する（例: `QuizCard コンポーネントを追加`）
- ブランチは原則 `main` を使用し、大きな機能追加の場合はフィーチャーブランチを切る

## コンポーネント命名規約

- **ファイル名**: PascalCase（例: `QuizCard.tsx`, `ScoreBoard.tsx`）
- **コンポーネント名**: ファイル名と一致させる
- **ページコンポーネント**: `Page` サフィックスを付ける（例: `TopPage.tsx`, `QuizPage.tsx`）
- **カスタムフック**: `use` プレフィックス（例: `useQuiz.ts`, `useScore.ts`）
- **ユーティリティ関数**: camelCase（例: `calcScore.ts`, `formatTime.ts`）
- **定数ファイル**: UPPER_SNAKE_CASE の変数名、ファイルは camelCase（例: `constants.ts` 内の `MAX_SCORE`）
