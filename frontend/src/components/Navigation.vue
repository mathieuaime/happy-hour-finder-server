<template>
  <ul>
    <router-link tag="li" to="/home" v-bind:class="{ active: isActive('home') }">
      <a>Happy Hour Finder</a>
    </router-link>
    <router-link tag="li" to="/bars" v-bind:class="{ active: isActive('bars') }">
      <a>Bars</a>
    </router-link>
    <router-link tag="li" to="/profile" class="profile">
      <a>Profile</a>
    </router-link>
  </ul>
</template>

<script>
  export default {
    name: 'navbar',
    data () {
      return {
        path: window.location.pathname
      }
    },
    methods: {
      isActive (page) {
        return this.currentPage === page
      }
    },
    computed: {
      currentPage () {
        if (this.path.length === 1) return ''
        const endIndex = this.path.indexOf('/', 1)
        return (endIndex === -1)
          ? this.path.substring(1)
          : this.path.substring(1, endIndex)
      }
    }
  }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
ul {
    list-style-type: none;
    width: 100%;
    margin: 0;
    padding: 0;
    overflow: hidden;
    background-color: #333;
    top: 0;
    position: -webkit-sticky; /* Safari */
    position: sticky;
}

li {
    float: left;
}

li a {
    display: block;
    color: white;
    text-align: center;
    padding: 14px 16px;
    text-decoration: none;
}

/* Change the link color to #111 (black) on hover */
li a:hover {
    background-color: #111;
}

.home {
    background-color: #222;
}

.active {
    background-color: #4CAF50;
}

.profile {
    float: right;
    background-color: #4CAF50;
}
</style>
