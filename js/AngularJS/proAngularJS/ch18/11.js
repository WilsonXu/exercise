angular.module("customServices", [])
.provider("logService", function() {
    return {
        $get: function() {
            return {
                messageCount: 0,
                log: function(msg) {
                    console.log("Debug: " + (this.messageCount++) + " " + msg);
                }
            };
        }
    };
});
