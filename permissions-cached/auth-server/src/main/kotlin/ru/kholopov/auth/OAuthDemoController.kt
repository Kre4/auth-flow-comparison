package ru.kholopov.auth

import org.springframework.http.MediaType
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseBody

@Controller
class OAuthDemoController {

	private val authorizeUrl =
		"/oauth2/authorize?response_type=code&client_id=demo-client" +
			"&redirect_uri=http%3A%2F%2F127.0.0.1%3A8080%2Fauthorized" +
			"&scope=api.read&state=demo"

	@GetMapping("/", produces = [MediaType.TEXT_HTML_VALUE])
	@ResponseBody
	fun home(): String = """
		<!DOCTYPE html>
		<html lang="ru">
		<head><meta charset="UTF-8"><title>Auth Server</title></head>
		<body>
		<h2>OAuth2 Authorization Server</h2>
		<p><a href="$authorizeUrl">Получить authorization code</a></p>
		</body>
		</html>
	""".trimIndent()

	@GetMapping("/authorized", produces = [MediaType.TEXT_HTML_VALUE])
	@ResponseBody
	fun authorized(
		@RequestParam(required = false) code: String?,
		@RequestParam(required = false) error: String?,
		@RequestParam(required = false) error_description: String?,
	): String {
		if (error != null) {
			return htmlPage("OAuth error: $error — ${error_description ?: "нет описания"}")
		}
		if (code.isNullOrBlank()) {
			return htmlPage("<a href=\"/\">Код не получен.</a>.")
		}
		return htmlPage(
			"""
			<h2>Authorization code</h2>
			<p><code id="code" style="word-break:break-all">$code</code></p>
			<p><button type="button" id="get-token">Получить access token</button></p>
			<pre id="result" style="white-space:pre-wrap;background:#f4f4f4;padding:1em"></pre>
			<p><a href="/">Получить новый code</a></p>
			<script>
			document.getElementById('get-token').onclick = async () => {
			  const out = document.getElementById('result');
			  const btn = document.getElementById('get-token');
			  const authCode = document.getElementById('code').textContent;
			  btn.disabled = true;
			  out.textContent = 'Запрос...';
			  try {
			    const body = new URLSearchParams({
			      grant_type: 'authorization_code',
			      code: authCode,
			      redirect_uri: 'http://127.0.0.1:8080/authorized'
			    });
			    const res = await fetch('/oauth2/token', {
			      method: 'POST',
			      headers: {
			        'Content-Type': 'application/x-www-form-urlencoded',
			        'Authorization': 'Basic ' + btoa('demo-client:demo-secret')
			      },
			      body: body
			    });
			    const text = await res.text();
			    try {
			      out.textContent = JSON.stringify(JSON.parse(text), null, 2);
			    } catch (_) {
			      out.textContent = res.ok ? text : ('Ошибка ' + res.status + ':\n' + text);
			    }
			  } catch (e) {
			    out.textContent = 'Ошибка: ' + e;
			  } finally {
			    btn.disabled = false;
			  }
			};
			</script>
			""".trimIndent(),
		)
	}

	private fun htmlPage(body: String): String = """
		<!DOCTYPE html>
		<html lang="ru">
		<head><meta charset="UTF-8"><title>Auth Server</title></head>
		<body>$body</body>
		</html>
	""".trimIndent()
}
