//v-bind : Style
var app = new Vue({
	el: '#b-style',
	data: {
		msg: '',
		rooms: []
	},
	created: function () {
		// this.showRoooms();
	},
	methods: {
		showRoooms: () => {
			axios.get('/chat/rooms')
				.then(res => {
					app.$data.rooms = [];
					for (let i = 0; i < res.data.length; i++) {
						console.log(JSON.stringify(res.data[i]));
						//app.$data.rooms.push(res.data[i]);
						app.$data.rooms.push(res.data[i]);
					}
				})
				.catch(err => {
					alert(err);
					console.log(err);
				})
		},
		createRoom: () => {
			let name = app.$data.msg;
			let form = new FormData();
			form.append('name', name);
			// alert(name);
			axios.post('/chat/room', form)
				.then(res => {
					app.$data.rooms.unshift(res.data);
				}).catch(err => {

				}).finally(() => {
					app.$data.msg = '';
				})
		}
	}
})

// app.showRoooms();