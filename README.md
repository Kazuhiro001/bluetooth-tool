# ETロボコン用BT通信ツール

## 目的
* ロギング機能
  - リアルタイム表示
  - ファイルへの保存
  - グラフ表示
* データ送信
  - 任意の文字列データ送信
  - プログラムの送信

## 動作環境
* JDK(JavaFX) 8u40 以上

## 実行方法
1. 必要ならばProxy設定を行う
  - rootディレクトリに `gradle.properties` ファイルを作成し以下を記載
  ```
  systemProp.http.proxyHost=<myproxy.co.jp>
  systemProp.http.proxyPort=<port.no>
  systemProp.https.proxyHost=<myproxy.co.jp>
  systemProp.https.proxyPort=<port.no>
  ```
2. 必要ならば、JDKの指定を行う
  - `gradle.properties` に以下を記載
  ```
  org.gradle.java.home = <JDK8のディレクトリ>
  ```
3. 以下のコマンドを実行
  * `./gradlew run`(linux or Cygwin)
  * `.\gradlew.bat run`(Windows)
