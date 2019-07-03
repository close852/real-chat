var app = new Vue({
	el: '#app',
	data: {
		name: 'Bob'
	},			
	methods: {
		toUpper: function() {
			this.name = this.name.toUpperCase()
		}
	}
})