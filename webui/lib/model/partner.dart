class Partner{
    int _id;
    String _name;
    String _urlWebSite;
    String _words;
    DateTime _created;
    String _numberPhone1;
    String _numberPhone2;
    String _numberPhone3;
    String _email1;
    String _email2;
    String _seat;

    Partner(this._id,this._name,this._urlWebSite,this._words,this._created,this._numberPhone1,this._numberPhone2
    ,this._numberPhone3,this._email1,this._email2,this._seat);

    set id(int id) => _id = id;
    int get id => _id;
    set name(String name) => _name = name;
    String get name => _name;
    String get words => _words;
    set created(DateTime created) => _created = created;
    DateTime get created => _created;
    set urlWebSite(String urlWebSite) => _urlWebSite = urlWebSite;
    String get urlWebSite => _urlWebSite;
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
    set seat(String seat) => _seat = seat;
    String get seat => _seat;
}

/***
 *  @Column(name = "nom",nullable = false)
    private String name;
    @Column(name = "url_web_site")
    private String urlWebSite;
    @Column(name = "libelle")
    private String words;
    @Column(name = "créer")
    private LocalDate created;
    @Column(name = "numero_1")
    private String numberPhone1;
    @Column(name = "numero_2")
    private String numberPhone2;
    @Column(name = "numero_3")
    private String numberPhone3;
    @Column(name = "mail_1")
    @Email
    private String email1;
    @Column(name = "mail_2")
    @Email
    private String email2;
    @Column(name = "siège")
    private String seat;
 */