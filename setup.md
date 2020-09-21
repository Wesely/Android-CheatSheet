# Most used setup commands

## Github SSH
`ssh-keygen -t rsa -b 4096 -C "wesely.ong@gmail.com"`

Create ssh config by 

`$ touch ~/.ssh/config`

Paste these configs (replace `id_rsa` with any filename)

```
Host *
  AddKeysToAgent yes
  UseKeychain yes
  IdentityFile ~/.ssh/id_rsa
```

## brew
`/bin/bash -c "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/master/install.sh)"`

## zsh
```
brew install zsh
echo 'export PATH="/usr/local/opt/ncurses/bin:$PATH"' >> ~/.zshrc
sh -c "$(curl -fsSL https://raw.github.com/ohmyzsh/ohmyzsh/master/tools/install.sh)"
```

Alias
```
# Git aliases
alias rb="git pull --rebase"
alias ck="git checkout"
alias stash="git stash"
alias apply="git stash apply"

# Android aliases
alias gc=".gradlew/clean"
alias gca="./gradlew clean assembleDebug"
alias gcb="./gradlew clean build"
alias cleanbuild="./gradlew clean build"
alias morning="
        gc
        gca
"
```

## Autojump
`brew install autojump`

And add this line to `~/.zshrc`

`[ -f /usr/local/etc/profile.d/autojump.sh ] && . /usr/local/etc/profile.d/autojump.sh`

## Arcanist
### Install
```
somewhere >> $ git clone https://github.com/phacility/libphutil.git
somewhere >> $ git clone https://github.com/phacility/arcanist.git
somewhere >> $ export PATH="$PATH:/Users/username/somewhere/arcanist/bin/"
```
Setup preferred editor
```
arc set-config editor "vim"
```

### Configure new project
```
$ cd yourproject/
yourproject/ $ arc install-certificate
```
