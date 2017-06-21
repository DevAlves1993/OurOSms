class AuthenticationRequest {
  String login;
  String password;

  AuthenticationRequest(this.login,this.password);
  AuthenticationRequest.init() {
    login = "";
    password = "";
  }

  Map<String,dynamic> toMap() {
    return {"login":login,"password":password};
  }
}
