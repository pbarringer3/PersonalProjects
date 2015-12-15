/**
 * Created by Pat on 12/15/2015.
 */
$(document).ready(function() {
    var numCats = 2;

    for(var i=0; i<numCats; i++) {
        $("body").append(
            "<div class='row fill-container'>" +
                "<div class='col-9'>" +
                    "<img src='images/cat"+i+".jpg' alt='Cat "+i+" picture' class='fill-container' id='cat"+i+"'>" +
                "</div>" +
                "<div class='col-3'>" +
                    "<div class='centered' id='counter"+i+"'>" +
                        "0" +
                    "</div>" +
                "</div>" +
            "</div>");
    }
    $("img").click(function(e) {
        var which = this.id.charAt(3);
        var counter = $("#counter"+which);
        var counterVal = parseInt(counter.text(),10);
        counterVal++;
        counter.text(counterVal);
    });
});
