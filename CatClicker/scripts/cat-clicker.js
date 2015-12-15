/**
 * Created by Pat on 12/15/2015.
 */
$(document).ready(function() {
    $("img").click(function(e) {
        var which = this.id.charAt(3);
        var counter = $("#counter"+which);
        var counterVal = parseInt(counter.text(),10);
        counterVal++;
        counter.text(counterVal);
    });
});
