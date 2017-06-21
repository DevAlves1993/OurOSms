class AuthenticationResponse {
  String _token;
  String _firstName;
  String _lastName;
  String _words;
  int _userId;

  AuthenticationResponse(this._token,this._firstName,this._lastName,this._words
    ,this._userId);
  AuthenticationResponse.fromMap(Map<String,dynamic> value) {
    if(value.containsKey("token"))
      this._token = value['token'];
    if(value.containsKey("firstName"))
      this._firstName = value['firstName'];
    if(value.containsKey("lastName"))
      this._lastName = value['lastName'];
    if(value.containsKey("words"))
      this._words = value['words'];
    if(value.containsKey("userId"))
      this._userId = value['userId'];
  }

  String get token => _token;
  void set token(String token) => _token = token;

  String get firstName => _firstName;
  void set firstName(String firstName) => _firstName = firstName;

  String get lastName => _lastName;
  void set lastName(String lastName) => _lastName = lastName;

  String get words => _words;
  void set words(String words) => _words = words;

  int get userId => _userId;
  void set userId(int id) => _userId = id;
}
