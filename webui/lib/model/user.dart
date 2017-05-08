class User {
    int _id;
    String _login;
    String _firstName; 
    String _lastName;
    String _words;
    DateTime _created;
    String _numberPhone1; 
    String _numberPhone2;
    String _email1; 
    String _email2;
    String _authority;

    User(this._id,this._login,this._firstName,this._lastName,this._words,this._created,this._numberPhone1
    ,this._numberPhone2,this._email1,this._email2,this._authority);

    User.fromJson(Map<String,dynamic> json)
    {
        int id = _asInt(json["id"]);
        if(id != -1)
        {
            this._id = id;
            this._login = json["login"];
            this._firstName = json["firstName"];
            this._lastName = json["lastName"];
            this._words = json["words"];
            this._created = _asDateTime(json["created"]);
            this._numberPhone1 = json["numberPhone1"];
            this._numberPhone2 = json["numberPhone2"];
            this._email1 = json["email1"];
            this._email2 = json["email2"];
            this._authority = json["authority"];
        }
    }

    int _asInt(String value) => value is int ? value : int.parse(value,onError: (s) => -1);
    DateTime _asDateTime(String value) => DateTime.parse(value);

    set id(int id) => _id = id;
    int get id => _id;
    set login(String login) => _login = login;
    String get login => _login;
    set firstName(String firstName) => _firstName = firstName;
    String get firstName => _firstName;
    set lastName(String lastName) => _lastName = lastName;
    String get lastName => _lastName;
    set words(String words) => _words = words;
    String get words => _words;
    set created(DateTime created) => _created = created;
    DateTime get created => _created;
    set numberPhone1(String numberPhone1) => _numberPhone1 = numberPhone1;
    String get numberPhone1 => _numberPhone1;
    set numberPhone2(String numberPhone2) => _numberPhone2 = numberPhone2;
    String get numberPhone2 => _numberPhone2;
    set email1(String email1) => _email1 = email1;
    String get email1 => _email1;
    set email2(String email2) => _email2 = email2;
    String get email2 => _email2;
    set authority(String authority) => _authority = authority;
    String get authority => _authority;
    
}

class UserInfo {
    String token;
    int userId;
    String firstName;
    String lastName;
    String words;

    UserInfo(this.token,this.userId,this.firstName,this.lastName,this.words);

    UserInfo.fromJson(Map<String,dynamic> json)
    {
        userId = _asInt(json["userId"]);
        if(userId != -1)
        {
            token = json["token"];
            firstName = json["firstName"];
            lastName = json["lastName"];
            words = json["words"];
        }
    }

    int _asInt(String value) => value is int ? value : int.parse(value,onError: (s) => -1);

}