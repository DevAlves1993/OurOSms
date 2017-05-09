class Customer {
    int _id;
    String _firstName;
    String _lastName;
    DateTime _created;
    String _words;
    String _numberPhone1;
    String _numberPhone2;
    String _numberPhone3;
    String _email1;
    String _email2;

    Customer(this._id,this._firstName,this._lastName,this._created,this._words,this._numberPhone1,this._numberPhone2
    ,this._numberPhone3,this._email1,this._email2);

    Customer.fromJson(Map<String,dynamic> json)
    {
        int id = _asInt(json["id"]);
        if(id != -1)
        {
            _id = id;
            _firstName = json["firstName"];
            _lastName = json["lastName"];
            _created = _asDateTime(json["created"]);
            _words = json["words"];
            _numberPhone1 = json["numberPhone1"];
            _numberPhone2 = json["numberPhone2"];
            _numberPhone3 = json["numberPhone3"];
            _email1 = json["email1"];
            _email2 = json["email2"];
        }
    }

    int _asInt(String value) => value is int ? value : int.parse(value,onError: (s) => -1);
    DateTime _asDateTime(String value) => DateTime.parse(value);

    set id(int id) => _id = id;
    int get id => _id;
    set firstName(String firstName) => _firstName = firstName;
    String get firstName => _firstName;
    set lastName(String lastName) => _lastName = lastName;
    String get lastName => _lastName;
    set created(DateTime created) => _created = created;
    DateTime get created => _created;
    set words(String words) => _words = words;
    String get words => _words;
    set numberPhone1(String numberPhone1) => _numberPhone1 = numberPhone1;
    set numberPhone2(String numberPhone2) => _numberPhone2 = numberPhone2;
    set numberPhone3(String numberPhone3) => _numberPhone3 = numberPhone3;
    String get numberPhone1 => _numberPhone1;
    String get numberPhone2 => _numberPhone2;
    String get numberPhone3 => _numberPhone3;
    set email1(String email1) => _email1 = email1;
    set email2(String email2) => _email2 = email2;
    String get email1 => _email1;
    String get email2 => _email2;
}


/*
@Column(name = "prenom")
    private String firstName;
    @Column(name = "nom")
    private String lastName;
    @Column(name = "créer")
    private LocalDate created;
    @Column(name = "libelle")
    private String words;
    @Column(name = "numero_1")
    private String numberPhone1;
    @Column(name = "numero_2")
    private String numberPhone2;
    @Column(name = "numero_3")
    private String numberPhone3;
    @Column(name = "mail_1")
    @Email
    private String mail1;
    @Column(name = "mail_2")
    @Email
    private String mail2;
 */