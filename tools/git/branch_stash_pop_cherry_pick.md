### 保存当前分支的修改
```
git stash
git stash list
git stash pop

git stash apply stash@{0}
```

#### 使用 cherry-pick
> 复制一个特定的提交到当前分支
```
 git cherry-pick $COMMIT_ID
```