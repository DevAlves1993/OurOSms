class SMS {
    int _id;
    String _content;
    String _senderName;
    String _senderAddress;
    String _country;

    SMS(this._id,this._content,this._senderName,this._senderAddress,this._country);

    SMS.fromJson(Map<String,dynamic> json)
    {

    }

    int _asInt(String value) => value is int ? value : int.parse(value,onError: (s) => -1);


    set id(int id) => _id = id;
    int get id => _id;
    set content(String content) =>_content;
    String get content => _content;
    set senderName(String senderName) => _senderAddress = senderName;
    String get senderName => _senderName;
    set senderAddress(String senderAddress) => _senderAddress = senderAddress;
    String get senderAddress => _senderAddress;
    set country(String country) => _country;
    String get country => _country;
}

/*
 private String receiver;
    // See link : https://fr.wikipedia.org/wiki/Short_Message_Service#Caract.C3.A9ristiques_techniques
    @Column(name = "contenu",length = 144)
    private String content;
    @Column(name = "nom_emetteur")
    private String senderName;
    @Column(name = "numero_emetteur")
    private String senderAddress;
    @Column(name = "pays")
    private String country; */