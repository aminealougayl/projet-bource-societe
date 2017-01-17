var express=require("express");
var app=express();
var http=require("http");
var httpServer=http.createServer(app);
var io=require("socket.io").listen(httpServer);
var bodyParser=require("body-parser");

app.use(bodyParser.json());

app.use(express.static('public'));
app.get('/',function(req,resp){
	resp.sendfile('public/index.html');
});

var amqp = require('amqplib/callback_api');

amqp.connect('amqp://localhost', function(err, conn) {
	conn.createChannel(function(err, ch) {
		var q = 'societe_queue';
		ch.assertQueue(q, {durable: false}, function(err, ok) {
			
		});
		console.log(" [*] Waiting for messages in %s. To exit press CTRL+C", q);
		ch.consume(q, function(msg) {
			io.sockets.emit('newSociete',{sc:msg.content.toString()});
			console.log(" [x] Received %s", msg.content.toString());
		}, {noAck: true});
	});
	
	conn.createChannel(function(err, ch) {
		var q = 'ordre_queue';
		ch.assertQueue(q, {durable: false}, function(err, ok) {
			
		});
		console.log(" [*] Waiting for messages in %s. To exit press CTRL+C", q);
		ch.consume(q, function(msg) {
			io.sockets.emit('newOrdre',{or:msg.content.toString()});
			console.log(" [x] Received %s", msg.content.toString());
		}, {noAck: true});
	});
	
});

io.on('connection', function(socket) {
	
    socket.on('chargerSocietes', function (data) {
        var options = {
            host: 'localhost',
            port: 8080,
            path: '/societes?page=' + data.page +'&size='+data.size
        };
        http.get(options, function(resp){
            body = '';
            resp.on('data', function(d){
                body += d;
            }).on('end', function(){
                var parsed = JSON.parse(body);
                //console.log(parsed);
                socket.emit('societesLoaded',{'societes':parsed.content,'totalPages':parsed.totalPages});
            });
        }).on("error", function(e){
            console.log("Erreur : " + e.message);
        });
    });
    
    socket.on('chargerSociete', function (data) {
        var options = {
            host: 'localhost',
            port: 8080,
            path: '/societes/' + data.codeSc
        };
        http.get(options, function(resp){
            body = '';
            resp.on('data', function(d){
                body += d;
            }).on('end', function(){
                var parsed = JSON.parse(body);
                //console.log(parsed);
                socket.emit('societeLoaded',{'societe':parsed});
            });
        }).on("error", function(e){
            console.log("Erreur : " + e.message);
        });
    });
    
    socket.on('chargerTotalVentes', function (data) {
        var options = {
            host: 'localhost',
            port: 8080,
            path: '/societes/' + data.codeSc + '/ventes/total'
        };
        http.get(options, function(resp){
            body = '';
            resp.on('data', function(d){
                body += d;
            }).on('end', function(){
                var parsed = JSON.parse(body);
                console.log(parsed);
                socket.emit('totalVentesLoaded',{'totalVentes':parsed});
            });
        }).on("error", function(e){
            console.log("Erreur : " + e.message);
        });
    });
    
    socket.on('chargerTotalAchats', function (data) {
        var options = {
            host: 'localhost',
            port: 8080,
            path: '/societes/' + data.codeSc + '/achats/total'
        };
        http.get(options, function(resp){
            body = '';
            resp.on('data', function(d){
                body += d;
            }).on('end', function(){
                var parsed = JSON.parse(body);
                console.log(parsed);
                socket.emit('totalAchatsLoaded',{'totalAchats':parsed});
            });
        }).on("error", function(e){
            console.log("Erreur : " + e.message);
        });
    });

    socket.on('chargerOrdres', function (data) {
        var options = {
            host: 'localhost',
            port: 8080,
            path: '/societes/' + data.codeSc + '/ordres?page=' + data.page + '&size=' + data.size
        };
        http.get(options, function(resp){
            body = '';
            resp.on('data', function(d){
                body += d;
            }).on('end', function(){
                var parsed = JSON.parse(body);
                console.log(parsed);
                socket.emit('ordresLoaded',{'ordres':parsed.content,'totalPages':parsed.totalPages});
            });
        }).on("error", function(e){
            console.log("Erreur : " + e.message);
        });
    });
    
    
});  

/*app.get('/societes',function(request,response){
	var body='';
	var url=request.url;
	
	http.get('http://localhost:8080/'+url,
			function(reqHTTP){
		
		reqHTTP.on('data', function(d) {
			body+=d;
		});
		reqHTTP.on('end', function() {
			response.jsonp(JSON.parse(body));
			io.sockets.emit('societes',{sc:JSON.parse(body)})
		});
	});
});

app.get('/societes/search',function(request,response){
	var body='';
	var url=request.url;
	
	http.get('http://localhost:8080'+url,
			function(reqHTTP){
		reqHTTP.on('data', function(d) {
			body+=d;
		});
		reqHTTP.on('end', function() {
			response.jsonp(JSON.parse(body));
		});
	});
});

app.get('/societes/:code',function(request,response){
	var code=request.params.code;
	var body='';
	var url=request.url;
	
	http.get('http://localhost:8080/societes/'+code,
			function(reqHTTP){
		reqHTTP.on('data', function(d) {
			body+=d;
		});
		reqHTTP.on('end', function() {
			response.jsonp(JSON.parse(body));
		});
	});
});

app.get('/societes/:code/ordres',function(request,response){
	var code=request.params.code;
	var body='';
	var url=request.url;
	
	http.get('http://localhost:8080'+url,
			function(reqHTTP){
		reqHTTP.on('data', function(d) {
			body+=d;
		});
		reqHTTP.on('end', function() {
			response.jsonp(JSON.parse(body));
		});
	});
	
});

app.get('/societes/:code/ventes/total',function(request,response){
	var code=request.params.code;
	var body='';
	var url=request.url;
	
	http.get('http://localhost:8080/societes/'+code+'/ventes/total',
			function(reqHTTP){
		reqHTTP.on('data', function(d) {
			body+=d;
		});
		reqHTTP.on('end', function() {
			response.jsonp(JSON.parse(body));
		});
	});
});
app.get('/societes/:code/achats/total',function(request,response){
	var code=request.params.code;
	var body='';
	var url=request.url;
	
	http.get('http://localhost:8080/societes/'+code+'/achats/total',
			function(reqHTTP){
		reqHTTP.on('data', function(d) {
			body+=d;
		});
		reqHTTP.on('end', function() {
			response.jsonp(JSON.parse(body));
		});
	});
});
app.get('/societes/:code/achats/avgPrix',function(request,response){
	var code=request.params.code;
	var body='';
	var url=request.url;
	
	http.get('http://localhost:8080/societes/'+code+'/achats/avgPrix',
			function(reqHTTP){
		reqHTTP.on('data', function(d) {
			body+=d;
		});
		reqHTTP.on('end', function() {
			response.jsonp(JSON.parse(body));
		});
	});
});
app.get('/societes/:code/ventes/avgPrix',function(request,response){
	var code=request.params.code;
	var body='';
	var url=request.url;
	
	http.get('http://localhost:8080/societes/'+code+'/ventes/avgPrix',
			function(reqHTTP){
		reqHTTP.on('data', function(d) {
			body+=d;
		});
		reqHTTP.on('end', function() {
			response.jsonp(JSON.parse(body));
		});
	});
});
app.get('/societes/:code/estimation',function(request,response){
	var code=request.params.code;
	var body='';
	var url=request.url;
	
	http.get('http://localhost:8080/societes/'+code+'/estimation',
			function(reqHTTP){
		reqHTTP.on('data', function(d) {
			body+=d;
		});
		reqHTTP.on('end', function() {
			response.jsonp(JSON.parse(body));
		});
	});
});*/

httpServer.listen(8686,function(){
	console.log("Server demarer sur port: 8686 ... ");
});