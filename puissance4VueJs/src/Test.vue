<template>
  <div id="conteneur">
    Text
  </div>
  <div>
    <button @click="buttonOn()">
      Essayer
    </button>
  </div>
</template>

<script>
export default {
  name: 'TestVue',
  methods: {
    async buttonOn() {
      document.getElementById('conteneur').classList.add("dropping");
      await new Promise(resolve => {
        setTimeout(resolve, 3000)
      });
      document.getElementById('conteneur').classList.remove("dropping");
    }
  }
};
</script>

<style scoped>
.dropping {
  animation: drop 3s linear infinite;
  font-family: Impact, Haettenschweiler, 'Arial Narrow Bold', sans-serif;
}

@keyframes drop {
  from {
    transform: translateY(-100%);
  }
  to {
    transform: translateY(0);
  }
}


</style>