/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var setCommand = (iCommand) => {

    var cmd = document.getElementById("command");
    cmd.value = iCommand;

};

var setCommand2 = (iCommand, selectedImage) => {
    setCommand(iCommand);
    var sImg = document.getElementById("selectedImage");
    sImg.value = selectedImage;
    document.getElementById('form').submit();
};
