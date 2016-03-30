/**
 * Created by Pat on 12/15/2015.
 */
var cats = ["Alfred", "Bob", "Cathy", "Doug", "Ender"];
var clickCount = [];

for(var i=0; i<cats.length; i++) {
    clickCount.push(0);
    $("#nameList").append("<li id='cat"+i+"'>"+cats[i]+"</li>");
}

$("li").click(function() {
    var which = this.id.charAt(3);
    displayCat(which);
});

function displayCat(num) {
    $("#catName").text(cats[num]);
    var $catPic = $("#catPic");
    $catPic.empty();
    $catPic.append("<img id='catImg"+num+"' class='fill-container' src='images/cat"+num+".jpg' alt='A picture of my cat "+cats[num]+"'>");
    $("#catImg"+num).click(incrementClickCount);
    $("#counter").text(clickCount[num]);
}

function incrementClickCount() {
    var index = this.id.charAt(6);
    clickCount[index]++;
    $("#counter").text(clickCount[index]);
}