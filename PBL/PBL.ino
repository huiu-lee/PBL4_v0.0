// 서버
#include <WiFi.h>
#include <WiFiClient.h>
#include <WebServer.h>
#include <ESPmDNS.h>
// 파이어베이스
#include <IOXhop_FirebaseESP32.h>
#include <IOXhop_FirebaseStream.h>

// 파이어베이스 정보
#define FIREBASE_HOST "arduino-test-e2e81-default-rtdb.firebaseio.com"
#define FIREBASE_AUTH "MHdDJGrIV5HpGR8OVdHKckiCNfC0IABMdBfcJpPA"

// 공유기 정보
const char* ssid = "KT_GiGA_2G_A5C5";
const char* password = "cxd94db004";

// 서버 포트
WebServer server(80);

const int led = 13;

void handleRoot() {  // handleClient(): 클라이언트 요청 시 처리 함수
  digitalWrite(led, 1);
  server.send(200, "text/plain", "hello from esp32!");
  digitalWrite(led, 0);
}

void handleNotFound() {
  digitalWrite(led, 1);
  String message = "File Not Found\n\n";
  message += "URI: ";
  message += server.uri();
  message += "\nMethod: ";
  message += (server.method() == HTTP_GET) ? "GET" : "POST";
  message += "\nArguments: ";
  message += server.args();
  message += "\n";
  for (uint8_t i = 0; i < server.args(); i++) {
    message += " " + server.argName(i) + ": " + server.arg(i) + "\n";
  }
  server.send(404, "text/plain", message);
  digitalWrite(led, 0);
}

void setup(void) {
  pinMode(led, OUTPUT);
  digitalWrite(led, 0);
  Serial.begin(115200);
  WiFi.mode(WIFI_STA);
  WiFi.begin(ssid, password);
  Serial.println("");

  // Wait for connection
  while (WiFi.status() != WL_CONNECTED) {
    delay(500);
    Serial.print(".");
  }
  Serial.println("");
  Serial.print("Connected to ");
  Serial.println(ssid);
  Serial.print("IP address: ");
  Serial.println(WiFi.localIP());

  if (MDNS.begin("esp32")) {
    Serial.println("MDNS responder started");
  }

  // create device control server

  server.on("/", handleRoot);

  server.on("/inline", []() {
    server.send(200, "text/plain", "this works as well");
  });
  
  server.onNotFound(handleNotFound);

  server.begin();
  Serial.println("HTTP server started");

  Firebase.begin(FIREBASE_HOST, FIREBASE_AUTH);
  Serial.println("FIREBASE connected");

}

// n 변수 선언?
int n = 0;

void loop(void) {
  server.handleClient();
  delay(2);//allow the cpu to switch to other tasks

  // 파이어베이스에 올릴 값
  Firebase.setFloat("number", 42.0);
  // handle error
  if (Firebase.failed()) {
      Serial.print("setting /number failed:");
      Serial.println(Firebase.error());  
      return;
  }
  delay(1000);

  // 파이어베이스에서 값 가져오기
//  Serial.print("number: ");
//  Serial.println(Firebase.getFloat("number"));
//  delay(1000);

  // 파이어베이스에서 값 삭제하기
//  Firebase.remove("number");
//  delay(1000);

  // set string value
  Firebase.setString("message", "hello, world!");
  // handle error
  if (Firebase.failed()) {
      Serial.print("setting /message failed:");
      Serial.println(Firebase.error());  
      return;
  }
  delay(1000);
  
  // set bool value
  Firebase.setBool("truth", false);
  // handle error
  if (Firebase.failed()) {
      Serial.print("setting /truth failed:");
      Serial.println(Firebase.error());  
      return;
  }
  delay(1000);

  // append a new value to /logs
  String name = Firebase.pushInt("logs", n++);
  // handle error
  if (Firebase.failed()) {
      Serial.print("pushing /logs failed:");
      Serial.println(Firebase.error());  
      return;
  }
  Serial.print("pushed: /logs/");
  Serial.println(name);
  delay(1000);
}
