
const ACCESS_TOKEN_KEY = 'accessToken';

const PUBLIC_API_PREFIXES = [
    '/api/members/login',
    '/api/members/join',
    '/api/tokens/refresh',
    '/api/tokens/logout'
];

function getAccessToken() {
    const token = localStorage.getItem(ACCESS_TOKEN_KEY);

    if (!token || token === 'null' || token === 'undefined') {
        return null;
    }

    return token;
}

function setAccessToken(token) {
    if (!token) {
        clearAccessToken();
        return;
    }
    localStorage.setItem(ACCESS_TOKEN_KEY, token);
}

function clearAccessToken() {
    localStorage.removeItem(ACCESS_TOKEN_KEY);
}

function parseAccessToken() {
    const token = getAccessToken();
    if (!token) return null;

    try {
        const payload = token.split('.')[1];
        const base64 = payload.replace(/-/g, '+').replace(/_/g, '/');
        const json = decodeURIComponent(
            atob(base64)
                .split('')
                .map((c) => '%' + c.charCodeAt(0).toString(16).padStart(2, '0'))
                .join('')
        );
        return JSON.parse(json);
    } catch (e) {
        return null;
    }
}

function getCurrentUserId() {
    const claims = parseAccessToken();
    return claims ? claims.sub : null;
}

function getCurrentUserName() {
    const claims = parseAccessToken();
    return claims ? claims.name : null;
}

function requireLogin(onReady) {
    if (getAccessToken() && parseAccessToken()) {
        if (onReady) onReady();
        return;
    }

    clearAccessToken();

    $.ajax({
        type: 'POST',
        url: '/api/tokens/refresh',
        success: (res) => {
            setAccessToken(res.accessToken);
            if (onReady) onReady();
        },
        error: () => {
            window.location.href = '/members/login';
        }
    });
}

$.ajaxSetup({
    beforeSend: function (xhr, settings) {
        const url = settings.url || '';
        if (!url.startsWith('/api/')) return;
        if (PUBLIC_API_PREFIXES.some((p) => url.startsWith(p))) return;

        const token = getAccessToken();
        if (token) {
            xhr.setRequestHeader('Authorization', 'Bearer ' + token);
        }
    }
});

$(document).ajaxError(function (event, xhr, settings) {
    const url = settings.url || '';
    if (!url.startsWith('/api/')) return;
    if (PUBLIC_API_PREFIXES.some((p) => url.startsWith(p))) return;

    if (xhr.status === 401) {
        clearAccessToken();
        window.location.href = '/members/login';
    }
});

function logout() {
    $.ajax({
        type: 'POST',
        url: '/api/tokens/logout',
        complete: function () {
            clearAccessToken();
            window.location.href = '/members/login';
        }
    });
}
