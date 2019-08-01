

// no longer needed jnr-ffi - https://mvnrepository.com/artifact/com.github.jnr/jnr-ffi/2.1.9
// jffi - http://repo1.maven.org/maven2/com/github/jnr/jffi/1.2.18/
// asm - https://mvnrepository.com/artifact/org.ow2.asm/asm/7.0

chromium.swt - https://github.com/maketechnology/chromium.swt
    chromium_subp-0700.exe and chromium_swt-0700.dll are stored using GIT LFS so don't just clone mindlessly as you just get stubs

//CEF Chrome downloads: http://opensource.spotify.com/cefbuilds/index.html
//    copy Debug or Release library plus all of Resources into same location as chrome.swt project's chromium_subp-0700.exe etc

Use their .p2 repository - http://dl.maketechnology.io/chromium-cef/rls/repository -> C:\Users\Paul\.p2\pool\plugins\com.make.chromium.cef.win32.win32.x86_64_0.4.0.201904250337.jar (for example)