# JVM languages toolkit
在.gitignore中排除了secrets.gradle
用户应当在本地工程目录中加入如下格式的secrets.gradle文件

```
ext {
    sonatypeUsername = "Your Sonatype UserName"
    sonatypePassword = "Your Sonatype Password"

    keyId = "the last 8 characters of your GPG key ID"
    secretKeyRingFile = "Path to your .gpg private Key Ring file"
    keyPassword = "your gpg Passphrase"
}
```