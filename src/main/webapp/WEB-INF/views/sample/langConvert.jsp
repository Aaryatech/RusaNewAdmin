<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 
 <html>
<head>
<script type="text/javascript" src="http://www.google.com/jsapi"></script>

<script type="text/javascript">
google.load("elements", "1", {packages: "transliteration"});
</script> 

<script>
function OnLoad() {                

                            var options = {
                                sourceLanguage:
                                google.elements.transliteration.LanguageCode.ENGLISH,
                                destinationLanguage:
                                [google.elements.transliteration.LanguageCode.HINDI],
                                shortcutKey: 'ctrl+g',
                                transliterationEnabled: true
                            };

                    var control = new
                    google.elements.transliteration.TransliterationControl(options);
                    control.makeTransliteratable(["txtEnglish"]);

    } //end onLoad function

    google.setOnLoadCallback(OnLoad);

</script> 


</head>
    <body>

       English Text: <input size="40" type="text" id="txtEnglish"/> <br/>
       Hindi Text : <input size="40" type="text" id="txtHindi"/> 

</body>
</html>