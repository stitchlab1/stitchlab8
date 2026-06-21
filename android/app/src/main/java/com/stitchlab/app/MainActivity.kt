package com.stitchlab.app

import android.os.Bundle
import android.webkit.PermissionRequest
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.ValueCallback
import android.webkit.ConsoleMessage
import android.webkit.JsResult
import android.webkit.JsPromptResult
import android.graphics.Bitmap
import android.view.View
import android.os.Message
import com.getcapacitor.BridgeActivity

class MainActivity : BridgeActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        // Wait for Capacitor's Bridge and WebView initialization to complete
        bridge?.apply {
            webView?.post {
                val originalClient = webView.webChromeClient
                if (originalClient != null) {
                    webView.webChromeClient = object : WebChromeClient() {
                        override fun onPermissionRequest(request: PermissionRequest?) {
                            if (request != null) {
                                val resources = request.resources
                                var hasAudio = false
                                for (res in resources) {
                                    if (PermissionRequest.RESOURCE_AUDIO_CAPTURE == res) {
                                        hasAudio = true
                                        break
                                    }
                                }
                                if (hasAudio) {
                                    // Auto-grant the microphone permission to WebView
                                    request.grant(arrayOf(PermissionRequest.RESOURCE_AUDIO_CAPTURE))
                                } else {
                                    // Delegate other resource permission requests to original client
                                    originalClient.onPermissionRequest(request)
                                }
                            } else {
                                super.onPermissionRequest(request)
                            }
                        }

                        override fun onPermissionRequestCanceled(request: PermissionRequest?) {
                            originalClient.onPermissionRequestCanceled(request)
                        }

                        // Delegate other methods to preserve all Capacitor bridge features & plugin interfaces
                        override fun onShowFileChooser(
                            webView: WebView?,
                            filePathCallback: ValueCallback<Array<android.net.Uri>>?,
                            fileChooserParams: FileChooserParams?
                        ): Boolean {
                            return originalClient.onShowFileChooser(webView, filePathCallback, fileChooserParams)
                        }

                        override fun onConsoleMessage(consoleMessage: ConsoleMessage?): Boolean {
                            return originalClient.onConsoleMessage(consoleMessage)
                        }

                        override fun onJsAlert(
                            view: WebView?,
                            url: String?,
                            message: String?,
                            result: JsResult?
                        ): Boolean {
                            return originalClient.onJsAlert(view, url, message, result)
                        }

                        override fun onJsConfirm(
                            view: WebView?,
                            url: String?,
                            message: String?,
                            result: JsResult?
                        ): Boolean {
                            return originalClient.onJsConfirm(view, url, message, result)
                        }

                        override fun onJsPrompt(
                            view: WebView?,
                            url: String?,
                            message: String?,
                            defaultValue: String?,
                            result: JsPromptResult?
                        ): Boolean {
                            return originalClient.onJsPrompt(view, url, message, defaultValue, result)
                        }

                        override fun onCreateWindow(
                            view: WebView?,
                            isDialog: Boolean,
                            isUserGesture: Boolean,
                            resultMsg: Message?
                        ): Boolean {
                            return originalClient.onCreateWindow(view, isDialog, isUserGesture, resultMsg)
                        }

                        override fun onCloseWindow(window: WebView?) {
                            originalClient.onCloseWindow(window)
                        }

                        override fun onProgressChanged(view: WebView?, newProgress: Int) {
                            originalClient.onProgressChanged(view, newProgress)
                        }

                        override fun onReceivedTitle(view: WebView?, title: String?) {
                            originalClient.onReceivedTitle(view, title)
                        }

                        override fun onReceivedIcon(view: WebView?, icon: Bitmap?) {
                            originalClient.onReceivedIcon(view, icon)
                        }

                        override fun onReceivedTouchIconUrl(
                            view: WebView?,
                            url: String?,
                            precomposed: Boolean
                        ) {
                            originalClient.onReceivedTouchIconUrl(view, url, precomposed)
                        }

                        override fun onShowCustomView(view: View?, callback: CustomViewCallback?) {
                            originalClient.onShowCustomView(view, callback)
                        }

                        override fun onHideCustomView() {
                            originalClient.onHideCustomView()
                        }
                    }
                }
            }
        }
    }
}
