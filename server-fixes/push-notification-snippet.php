<?php /* Inject this inside <head> in your site's header/index.php */ ?>
<script>
document.addEventListener("DOMContentLoaded", () => {
    if (window.Capacitor && window.Capacitor.Plugins && window.Capacitor.Plugins.PushNotifications) {
        const PushNotifications = window.Capacitor.Plugins.PushNotifications;
        PushNotifications.requestPermissions().then(result => {
            if (result.receive === 'granted') {
                PushNotifications.register();
            }
        });
    }
});
</script>
