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

## Autojump
`brew install autojump`

And add this line to `~/.zshrc`

`[ -f /usr/local/etc/profile.d/autojump.sh ] && . /usr/local/etc/profile.d/autojump.sh`
