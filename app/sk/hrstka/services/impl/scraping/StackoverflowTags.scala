package sk.hrstka.services.impl.scraping

object StackoverflowTags {
  def meaningful = all.diff(blacklist)

  private val blacklist: Set[String] = Set(
    "refactoring",
    "sed",
    "this",
    "design",
    "workflow",
    "base64",
    "map",
    "methods",
    "image",
    "project",
    "process",
    "interface",
    "adobe",
    "service",
    "testing",
    "web-applications",
    "numbers",
    "grid",
    "web",
    "fork",
    "colors",
    "focus",
    "date",
    "locking",
    "client",
    "resources",
    "position",
    "prototype",
    "types",
    "mobile",
    "call",
    "width",
    "user",
    "dynamic",
    "system",
    "excel",
    "powerpoint",
    "module",
    "profiling",
    "connection",
    "ip",
    "video",
    "server",
    "dns",
    "apple",
    "oracle",
    "int",
    "data",
    "backup",
    "templates",
    "response",
    "cloud",
    "post",
    "field",
    "range",
    "export",
    "constraints",
    "maps",
    "com",
    "networking",
    "build",
    "include",
    "storage",
    "closures",
    "block",
    "operators",
    "components",
    "protocols",
    "architecture",
    "background",
    "server",
    "contextmenu",
    "automation",
    "compilation",
    "locale",
    "task",
    "string",
    "internationalization",
    "driver",
    "mapping",
    "plot",
    "translation",
    "scale",
    "security",
    "background-image",
    "constants",
    "processing",
    "forms",
    "email",
    "selection",
    "integration",
    "port",
    "case",
    "foreign-keys",
    "model",
    "directory",
    "submit",
    "constants",
    "certificate",
    "registry",
    "table",
    "documentation",
    "refresh",
    "object",
    "deployment",
    "dom",
    "select",
    "iteration",
    "testng",
    "report",
    "logic",
    "reporting",
    "select",
    "share",
    "get",
    "find",
    "dll",
    "key",
    "list",
    "tabs",
    "request",
    "hash",
    "make",
    "odata",
    "accessibility",
    "statistics",
    "hover",
    "package",
    "database",
    "join",
    "outlook",
    "entity",
    "plugins",
    "frameworks",
    "ide",
    "api",
    "wifi",
    "integration-testing",
    "selector",
    "char",
    "scheduled-tasks",
    "group",
    "events",
    "time",
    "set",
    "function",
    "associations",
    "orientation",
    "gps",
    "servicestack",
    "migration",
    "singleton",
    "coordinates",
    "progress-bar",
    "dataset",
    "const",
    "charts",
    "linker",
    "escaping",
    "panel",
    "match",
    "tree",
    "shader",
    "function",
    "configuration",
    "view",
    "scripting",
    "action",
    "conditional",
    "themes",
    "debugging",
    "converter",
    "listview",
    "audio",
    "text",
    "save",
    "structure",
    "order",
    "servlets",
    "database-design",
    "timer",
    "containers",
    "widget",
    "air",
    "stack",
    "inheritance",
    "structure",
    "algorithm",
    "installation",
    "performance",
    "session",
    "row",
    "devise",
    "admin",
    "gmail",
    "timer",
    "install",
    "location",
    "proxy",
    "load",
    "menu",
    "sms",
    "listener",
    "limit",
    "format",
    "oop",
    "count",
    "click",
    "generics",
    "expression",
    "transform",
    "xslt",
    "hosting",
    "buffer",
    "each",
    "math",
    "mips",
    "orm",
    "merge",
    "website",
    "import",
    "static",
    "collections",
    "cmake",
    "scope",
    "each",
    "terminal",
    "size",
    "limit",
    "authentication",
    "shell",
    "ipad",
    "pointers",
    "accordion",
    "routes",
    "path",
    "validation",
    "installer",
    "reference",
    "youtube",
    "unique",
    "line",
    "group-by",
    "transactions",
    "client-server"
  )

  private val all: Set[String] = Set(
    "javascript",
    "java",
    "c#",
    "php",
    "android",
    "jquery",
    "python",
    "html",
    "c++",
    "ios",
    "mysql",
    "css",
    "sql",
    "asp.net",
    "objective-c",
    "ruby-on-rails",
    ".net",
    "iphone",
    "c",
    "arrays",
    "ruby",
    "sql-server",
    "json",
    "ajax",
    "regex",
    "angularjs",
    "xml",
    "asp.net-mvc",
    "linux",
    "r",
    "wpf",
    "django",
    "database",
    "node.js",
    "xcode",
    "eclipse",
    "vb.net",
    "windows",
    "string",
    "html5",
    "wordpress",
    "excel",
    "multithreading",
    "spring",
    "facebook",
    "image",
    "forms",
    "oracle",
    "winforms",
    "git",
    "osx",
    "bash",
    "swift",
    "algorithm",
    "performance",
    "apache",
    "swing",
    "ruby-on-rails-3",
    "matlab",
    "twitter-bootstrap",
    "entity-framework",
    "mongodb",
    "linq",
    "vba",
    "hibernate",
    "perl",
    "visual-studio",
    "web-services",
    "css3",
    "visual-studio-2010",
    "list",
    "postgresql",
    ".htaccess",
    "qt",
    "sql-server-2008",
    "wcf",
    "sqlite",
    "scala",
    "function",
    "file",
    "uitableview",
    "shell",
    "codeigniter",
    "validation",
    "actionscript-3",
    "asp.net-mvc-3",
    "python-2.7",
    "cordova",
    "api",
    "class",
    "google-maps",
    "rest",
    "jsp",
    "maven",
    "sockets",
    "excel-vba",
    "unit-testing",
    "tsql",
    "google-chrome",
    "xaml",
    "security",
    "asp.net-mvc-4",
    "symfony2",
    "cocoa",
    "email",
    "delphi",
    "flash",
    "jquery-ui",
    "ipad",
    "user-interface",
    "google-app-engine",
    "android-layout",
    "cocoa-touch",
    "oop",
    "sorting",
    "loops",
    "date",
    "magento",
    "http",
    "parsing",
    "internet-explorer",
    "listview",
    "spring-mvc",
    "debugging",
    "templates",
    "variables",
    "powershell",
    "session",
    "csv",
    "jsf",
    "silverlight",
    "unix",
    "datetime",
    "laravel",
    "opencv",
    "pointers",
    "batch-file",
    "facebook-graph-api",
    "object",
    "ms-access",
    "tomcat",
    "url",
    "winapi",
    "ruby-on-rails-4",
    "firefox",
    "c++11",
    "ubuntu",
    "python-3.x",
    "cakephp",
    "haskell",
    "authentication",
    "events",
    "flex",
    "azure",
    "table",
    "opengl",
    "pdf",
    "exception",
    "animation",
    "java-ee",
    "generics",
    "servlets",
    "grails",
    "mod-rewrite",
    "jpa",
    "redirect",
    "hadoop",
    "selenium",
    "xslt",
    "svn",
    "dom",
    "caching",
    "if-statement",
    "inheritance",
    "search",
    "select",
    "jquery-mobile",
    "c#-4.0",
    "button",
    "iis",
    "math",
    "visual-studio-2012",
    "for-loop",
    "post",
    "android-fragments",
    "visual-c++",
    "testing",
    "ssl",
    "audio",
    "gcc",
    "numpy",
    "design-patterns",
    "windows-phone-7",
    "join",
    "android-intent",
    "stored-procedures",
    "curl",
    "amazon-web-services",
    "core-data",
    "android-activity",
    "dictionary",
    "networking",
    "sharepoint",
    "iframe",
    "image-processing",
    "extjs",
    "memory",
    "video",
    "gridview",
    "zend-framework",
    "razor",
    "model-view-controller",
    "logging",
    "activerecord",
    "backbone.js",
    "canvas",
    "gwt",
    "mobile",
    "asynchronous",
    "cookies",
    "windows-phone-8",
    "optimization",
    "assembly",
    "encryption",
    "serialization",
    "recursion",
    "layout",
    "express",
    "vim",
    "heroku",
    "memory-management",
    "sql-server-2005",
    "xpath",
    "soap",
    "twitter",
    "nhibernate",
    "file-upload",
    "browser",
    "jdbc",
    "ember.js",
    "design",
    "boost",
    "text",
    "google-maps-api-3",
    "netbeans",
    "matrix",
    "web",
    "dynamic",
    "pandas",
    "d3.js",
    "drupal",
    "file-io",
    "visual-studio-2013",
    "svg",
    "checkbox",
    "meteor",
    "mvvm",
    "dll",
    "database-design",
    "random",
    "nginx",
    "multidimensional-array",
    "plugins",
    "vector",
    "methods",
    "android-studio",
    "web-applications",
    "arraylist",
    "visual-studio-2008",
    "input",
    "github",
    "android-listview",
    "ios7",
    "reflection",
    "indexing",
    "knockout.js",
    "jsf-2",
    "data-structures",
    "asp.net-web-api",
    "parse.com",
    "data-binding",
    "drop-down-menu",
    "unicode",
    "mysqli",
    "yii",
    "service",
    "pdo",
    "matplotlib",
    "reporting-services",
    "primefaces",
    "time",
    "hash",
    "intellij-idea",
    "emacs",
    "view",
    "deployment",
    "encoding",
    "laravel-4",
    "uiview",
    "login",
    "collections",
    "scroll",
    "replace",
    "graphics",
    "linq-to-sql",
    "groovy",
    "unity3d",
    "command-line",
    "awk",
    "joomla",
    "printing",
    "paypal",
    "data",
    "graph",
    "jenkins",
    "elasticsearch",
    "ant",
    "selenium-webdriver",
    "struct",
    "utf-8",
    "django-models",
    "ssh",
    "plot",
    "solr",
    "ios5",
    "datagridview",
    "tcp",
    "javascript-events",
    "jquery-plugins",
    "sed",
    "syntax",
    "memory-leaks",
    "rspec",
    "types",
    "google-chrome-extension",
    "go",
    "vbscript",
    "hyperlink",
    "junit",
    "gradle",
    "binding",
    "uiviewcontroller",
    "angularjs-directive",
    "webview",
    "foreach",
    "version-control",
    "orm",
    "fonts",
    "charts",
    "plsql",
    "oauth",
    "highcharts",
    "amazon-s3",
    "jar",
    "ios4",
    "tfs",
    "twitter-bootstrap-3",
    "javafx",
    "concurrency",
    "timer",
    "import",
    "amazon-ec2",
    "opengl-es",
    "process",
    "properties",
    "parameters",
    "colors",
    "https",
    "filter",
    "spring-security",
    "error-handling",
    "google-apps-script",
    "combobox",
    "menu",
    "constructor",
    "playframework",
    "lambda",
    "interface",
    "datagrid",
    "doctrine2",
    "bitmap",
    "dependency-injection",
    "cocos2d-iphone",
    "windows-8",
    "configuration",
    "jboss",
    "casting",
    "clojure",
    "build",
    "exception-handling",
    "download",
    "oracle11g",
    "datatable",
    "architecture",
    "enums",
    "ios8",
    "devise",
    "android-asynctask",
    "model",
    "autocomplete",
    "kendo-ui",
    "cmd",
    "coldfusion",
    "google-analytics",
    "windows-7",
    "io",
    "url-rewriting",
    "module",
    "map",
    "responsive-design",
    "triggers",
    "ggplot2",
    "while-loop",
    "ssis",
    "struts2",
    "stl",
    "background",
    "sqlite3",
    "reference",
    "static",
    "proxy",
    "flask",
    "asp-classic",
    "webforms",
    "youtube",
    "scripting",
    "scope",
    "compiler-construction",
    "merge",
    "xsd",
    "character-encoding",
    "sdk",
    "3d",
    "upload",
    "tkinter",
    "jqgrid",
    "tabs",
    "path",
    "safari",
    "nullpointerexception",
    "count",
    "xamarin",
    "split",
    "lua",
    "uiscrollview",
    "compilation",
    "bluetooth",
    "transactions",
    "cuda",
    "uiwebview",
    "insert",
    "ios6",
    "terminal",
    "ftp",
    "iis-7",
    "com",
    "permissions",
    "callback",
    "compiler-errors",
    "blackberry",
    "mfc",
    "sql-server-2008-r2",
    "actionscript",
    "controller",
    "textbox",
    "parallel-processing",
    "xml-parsing",
    "websocket",
    "makefile",
    "asp.net-mvc-2",
    "phpmyadmin",
    "internet-explorer-8",
    "null",
    "mongoose",
    "neo4j",
    "get",
    "dns",
    "localization",
    "eclipse-plugin",
    "tree",
    "f#",
    "datepicker",
    "linux-kernel",
    "camera",
    "ms-word",
    "sharepoint-2010",
    "crystal-reports",
    "pagination",
    "ffmpeg",
    ".net-4.0",
    "sql-server-2012",
    "android-ndk",
    "dojo",
    "linked-list",
    "mapreduce",
    "drag-and-drop",
    "user-controls",
    "coffeescript",
    "routing",
    "drupal-7",
    "msbuild",
    "installation",
    "frameworks",
    "header",
    "vb6",
    "calendar",
    "lucene",
    "linker",
    "uinavigationcontroller",
    "stream",
    "routes",
    "active-directory",
    "listbox",
    "notifications",
    "outlook",
    "onclick",
    "grep",
    "dialog",
    "crash",
    "group-by",
    "asp.net-mvc-5",
    "uiimageview",
    "machine-learning",
    "delegates",
    "redis",
    "navigation",
    "windows-runtime",
    "cron",
    "namespaces",
    "push-notification",
    "cassandra",
    "jaxb",
    "mono",
    "binary",
    "air",
    "socket.io",
    "floating-point",
    "entity-framework-4",
    "functional-programming",
    "rotation",
    "uibutton",
    "jquery-selectors",
    "android-actionbar",
    "event-handling",
    "keyboard",
    "internationalization",
    "language-agnostic",
    "android-emulator",
    "ado.net",
    "console",
    "zend-framework2",
    "automation",
    "sass",
    "gps",
    "libgdx",
    "three.js",
    "oauth-2.0",
    "cross-browser",
    "annotations",
    "geolocation",
    "monotouch",
    "network-programming",
    "telerik",
    "android-edittext",
    "apache-spark",
    "garbage-collection",
    "google-api",
    "macros",
    "website",
    "mercurial",
    "xna",
    "tags",
    "gruntjs",
    "django-forms",
    "ide",
    "attributes",
    "sprite-kit",
    "ruby-on-rails-3.2",
    "applet",
    "iterator",
    "wordpress-plugin",
    "xcode6",
    "wsdl",
    "formatting",
    "arduino",
    "grid",
    "sqlalchemy",
    "windows-phone-8.1",
    "popup",
    "django-templates",
    "hashmap",
    "gem",
    "resources",
    "coding-style",
    "textview",
    "css-selectors",
    "directory",
    "docker",
    "openssl",
    "web-scraping",
    "char",
    "uiimage",
    "resize",
    "include",
    "cryptography",
    "cmake",
    "serial-port",
    "nsstring",
    "x86",
    "hover",
    "rss",
    "passwords",
    "doctrine",
    "operating-system",
    "wix",
    "jframe",
    "widget",
    "jtable",
    "http-headers",
    "windows-phone",
    "modal-dialog",
    "imageview",
    "jvm",
    "windows-services",
    "storyboard",
    "numbers",
    "nosql",
    "nested",
    "seo",
    "smtp",
    "glassfish",
    "centos",
    "google-spreadsheet",
    "laravel-5",
    "youtube-api",
    "web-config",
    "ef-code-first",
    "ldap",
    "windows-store-apps",
    "segmentation-fault",
    "mocking",
    "timezone",
    "android-viewpager",
    "jersey",
    "integer",
    "statistics",
    "xhtml",
    "prolog",
    "request",
    "scipy",
    "excel-formula",
    "click",
    "playframework-2.0",
    "slider",
    "autolayout",
    "requirejs",
    "google-drive-sdk",
    "java-me",
    "erlang",
    "jni",
    "order",
    "reactjs",
    "webkit",
    "access-vba",
    "angularjs-scope",
    "make",
    "copy",
    "save",
    "initialization",
    "treeview",
    "data.frame",
    "position",
    "udp",
    "pyqt",
    "styles",
    "synchronization",
    "jasper-reports",
    "int",
    ".net-3.5",
    "xmlhttprequest",
    "radio-button",
    "rubygems",
    "hive",
    "window",
    "find",
    "xcode4",
    "geometry",
    "gdb",
    "cursor",
    "firefox-addon",
    "documentation",
    "thread-safety",
    "npm",
    "xampp",
    "timestamp",
    "sum",
    "sql-update",
    "webdriver",
    "format",
    "connection",
    "log4j",
    "html5-canvas",
    "woocommerce",
    "dart",
    "content-management-system",
    "stack",
    "set",
    "oracle10g",
    "command",
    "filesystems",
    "dependencies",
    "jquery-validate",
    "g++",
    "db2",
    "linq-to-entities",
    "timeout",
    "switch-statement",
    "ruby-on-rails-3.1",
    "silverlight-4.0",
    "typescript",
    "fluent-nhibernate",
    "extjs4",
    "location",
    "fortran",
    "icons",
    "arm",
    "return",
    "boolean",
    "windows-installer",
    "ckeditor",
    "jackson",
    "datatables",
    "in-app-purchase",
    "django-admin",
    "foreign-keys",
    "kernel",
    "gmail",
    "maven-2",
    "uitextfield",
    "gtk",
    "google-play",
    "user",
    "broadcastreceiver",
    "jpanel",
    "sms",
    "escaping",
    "arguments",
    "android-widget",
    "locking",
    "load",
    "spring-boot",
    "uikit",
    "jquery-animate",
    "ip",
    "polymorphism",
    "tinymce",
    "dataset",
    "server",
    "uicollectionview",
    "sas",
    "duplicates",
    "full-text-search",
    "cygwin",
    "embedded",
    "singleton",
    "alignment",
    "queue",
    "pthreads",
    "compare",
    "qt4",
    "certificate",
    "zip",
    "beautifulsoup",
    "interface-builder",
    "migration",
    "django-views",
    "repository",
    "webserver",
    "phpunit",
    "compression",
    "app-store",
    "ejb",
    "usb",
    "apache2",
    "eloquent",
    "refactoring",
    "touch",
    "less",
    "ionic-framework",
    "nsmutablearray",
    "hex",
    "json.net",
    "jasmine",
    "imagemagick",
    "continuous-integration",
    "pygame",
    "comparison",
    "wxpython",
    "odbc",
    "raspberry-pi",
    "64bit",
    "applescript",
    "css-float",
    "installer",
    "focus",
    "wpf-controls",
    "subquery",
    "export",
    "maps",
    "fancybox",
    "debian",
    "printf",
    "append",
    "key",
    "sencha-touch",
    "package",
    "sbt",
    "devexpress",
    "install",
    "label",
    "jms",
    "mapping",
    "google-visualization",
    "nsarray",
    "c++-cli",
    "scheme",
    "swt",
    "client",
    "wso2",
    "web-crawler",
    "salesforce",
    "titanium",
    "localhost",
    "double",
    "height",
    "many-to-many",
    "uitabbarcontroller",
    "type-conversion",
    "java-8",
    "streaming",
    "themes",
    "registry",
    "jetty",
    "runtime",
    "base64",
    "uilabel",
    "malloc",
    "internet-explorer-9",
    "awt",
    "size",
    "computer-vision",
    "scrapy",
    "google-cloud-messaging",
    "xmpp",
    "ssrs-2008",
    "client-server",
    "liferay",
    "video-streaming",
    "nlp",
    "http-post",
    "pattern-matching",
    "fragment",
    "drawing",
    "latex",
    "angular-ui-router",
    "report",
    "admob",
    "tdd",
    "global-variables",
    "operator-overloading",
    "async-await",
    "pipe",
    "httpwebrequest",
    "logic",
    "sinatra",
    "pdf-generation",
    "jmeter",
    "twig",
    "angularjs-ng-repeat",
    "cross-domain",
    "jstl",
    "rewrite",
    "syntax-error",
    "tomcat7",
    "internet-explorer-7",
    "interop",
    "progress-bar",
    "apple-push-notifications",
    "javafx-2",
    "xcode5",
    "width",
    "authorization",
    "range",
    "apache-camel",
    "wordpress-theming",
    "ember-data",
    "textarea",
    "linq-to-xml",
    "vagrant",
    "editor",
    "cucumber",
    "directx",
    "comments",
    "bit-manipulation",
    "operators",
    "closures",
    "lisp",
    "expression",
    "associations",
    "rabbitmq",
    "out-of-memory",
    "asp.net-ajax",
    "memcached",
    "phantomjs",
    "tooltip",
    "websphere",
    "monodroid",
    "html-parsing",
    "firebase",
    "android-webview",
    "symfony1",
    "osgi",
    "2d",
    "kendo-grid",
    "mkmapview",
    "xml-serialization",
    "uml",
    "multiprocessing",
    "preg-replace",
    "iteration",
    "database-connection",
    "media-queries",
    "flex4",
    "subprocess",
    "runtime-error",
    "mongoid",
    "ms-access-2007",
    "apple",
    "ms-access-2010",
    "entity-framework-6",
    "cloud",
    "html5-video",
    "nunit",
    "signalr",
    "cors",
    "entity",
    "character",
    "html-lists",
    "sencha-touch-2",
    "png",
    "substring",
    "dynamics-crm-2011",
    "output",
    "adobe",
    "microsoft-metro",
    "tortoisesvn",
    "underscore.js",
    "facebook-javascript-sdk",
    "couchdb",
    "try-catch",
    "mule",
    "profiling",
    "tcl",
    "pivot",
    "cgi",
    "shared-libraries",
    "weblogic",
    "environment-variables",
    "ios-simulator",
    "uitextview",
    "views",
    "polymer",
    "apache-poi",
    "entity-framework-5",
    "jax-rs",
    "submit",
    "promise",
    "fullcalendar",
    "structure",
    "task-parallel-library",
    "textures",
    "field",
    "preg-match",
    "glsl",
    "schema",
    "sitecore",
    "uinavigationbar",
    "byte",
    "gson",
    "drupal-6",
    "itextsharp",
    "programming-languages",
    "android-camera",
    "decimal",
    "gulp",
    "google-plus",
    "scrollbar",
    "mingw",
    "iis-7.5",
    "local-storage",
    "clang",
    "cxf",
    "signals",
    "xpages",
    "eclipse-rcp",
    "hbase",
    "constraints",
    "android-service",
    "wifi",
    "max",
    "richfaces",
    "sharedpreferences",
    "domain-driven-design",
    "windows-8.1",
    "mouseevent",
    "relational-database",
    "open-source",
    "cross-platform",
    "nuget",
    "sublimetext2",
    "override",
    "nsdate",
    "sql-order-by",
    "core-graphics",
    "row",
    "jsoup",
    "tuples",
    "itext",
    "buffer",
    "mapkit",
    "excel-2010",
    "sails.js",
    "yii2",
    "refresh",
    "ionic",
    "tfs2010",
    "persistence",
    "http-status-code-404",
    "sonarqube",
    "cakephp-2.0",
    "servicestack",
    "capybara",
    "toggle",
    "rake",
    "winrt-xaml",
    "left-join",
    "screen",
    "paperclip",
    "wamp",
    "core-animation",
    "automated-tests",
    "apache-pig",
    "rendering",
    "backup",
    "control",
    "jsonp",
    "zurb-foundation",
    "gae-datastore",
    "ssl-certificate",
    "facebook-like",
    "pip",
    "struts",
    "scrollview",
    "webbrowser-control",
    "uri",
    "vsto",
    "odata",
    "android-sqlite",
    "bytearray",
    "eclipselink",
    "ocaml",
    "exec",
    "prepared-statement",
    "httprequest",
    "webgl",
    "actionbarsherlock",
    "magento-1.7",
    "workflow",
    "classpath",
    "overflow",
    "tableview",
    "sql-server-ce",
    "notepad++",
    "qml",
    "akka",
    "windows-xp",
    "teamcity",
    "elisp",
    "ms-office",
    "ascii",
    "background-image",
    "rvm",
    "composer-php",
    "border",
    "task",
    "components",
    "wolfram-mathematica",
    "hide",
    "echo",
    "text-files",
    "concatenation",
    "match",
    "query-optimization",
    "scheduled-tasks",
    "parameter-passing",
    "ipython",
    "automatic-ref-counting",
    "jax-ws",
    "smarty",
    "pyqt4",
    "scikit-learn",
    "version",
    "custom-controls",
    "e-commerce",
    "handlebars.js",
    "listener",
    "blob",
    "cocos2d-x",
    "case",
    "worklight",
    "zoom",
    "jbutton",
    "prototype",
    "folder",
    "fork",
    "adb",
    "metadata",
    "conditional",
    "single-sign-on",
    "line",
    "nsdictionary",
    "chef",
    "chat",
    "asp.net-mvc-routing",
    "android-manifest",
    "mp3",
    "aes",
    "gnuplot",
    "heap",
    "subdomain",
    "coordinates",
    "apk",
    "resharper",
    "orientation",
    "opencl",
    "google-play-services",
    "windows-mobile",
    "c#-3.0",
    "deserialization",
    "port",
    "filtering",
    "sharepoint-2013",
    "opengl-es-2.0",
    "asp.net-membership",
    "avfoundation",
    "warnings",
    "const",
    "sharepoint-2007",
    "spinner",
    "console-application",
    "visual-studio-2005",
    "css-position",
    "sprite",
    "settings",
    "driver",
    "phonegap-plugins",
    "branch",
    "action",
    "hql",
    "flash-builder",
    "neural-network",
    "hosting",
    "rust",
    "posix",
    "gorm",
    "mobile-safari",
    "hook",
    "ninject",
    "visual-studio-2015",
    "android-animation",
    "facebook-fql",
    "anchor",
    "this",
    "activex",
    "artificial-intelligence",
    "typo3",
    "gzip",
    "virtual-machine",
    "android-linearlayout",
    "mpi",
    "converter",
    "data.table",
    "controls",
    "storage",
    "activemq",
    "contextmenu",
    "undefined",
    "sdl",
    "shader",
    "testng",
    "call",
    "inversion-of-control",
    "afnetworking",
    "excel-2007",
    "push",
    "mouse",
    "django-rest-framework",
    "bundle",
    "jade",
    "media-player",
    "hdfs",
    "system",
    "entity-framework-4.1",
    "android-gradle",
    "opencart",
    "group",
    "wmi",
    "shiny",
    "keyboard-shortcuts",
    "time-series",
    "rsa",
    "capistrano",
    "simplexml",
    "cypher",
    "updatepanel",
    "embed",
    "mockito",
    "jboss7.x",
    "celery",
    "uiimagepickercontroller",
    "overlay",
    "google-maps-markers",
    "compact-framework",
    "ssas",
    "common-lisp",
    "jpeg",
    "facebook-php-sdk",
    "amazon",
    "dynamics-crm",
    "xslt-1.0",
    "gallery",
    "inner-join",
    "iis-6",
    "connection-string",
    "maven-3",
    "css-transitions",
    "httpclient",
    ".net-4.5",
    "transform",
    "iphone-sdk-3.0",
    "itunesconnect",
    "nsurlconnection",
    "forms-authentication",
    "integration",
    "project",
    "containers",
    "hashtable",
    "html-table",
    "preprocessor",
    "asset-pipeline",
    "flex3",
    "raphael",
    "protractor",
    "spring-data",
    "processing",
    "protocols",
    "naming-conventions",
    "binary-tree",
    "openshift",
    "diff",
    "pinvoke",
    "share",
    "java.util.scanner",
    "command-line-interface",
    "yaml",
    "datasource",
    "google-calendar",
    "url-routing",
    "android-arrayadapter",
    "integration-testing",
    "clr",
    "javamail",
    "newline",
    "special-characters",
    "static-libraries",
    "openerp",
    "ubuntu-12.04",
    "oledb",
    "dataframes",
    "adapter",
    "haml",
    "unique",
    "wicket",
    "customization",
    "passenger",
    "instance",
    "plist",
    "query-string",
    "locale",
    "android-imageview",
    "llvm",
    "admin",
    "cluster-computing",
    "qt5",
    "translation",
    "joomla2.5",
    "block",
    "android-canvas",
    "google-chrome-devtools",
    "virtualbox",
    "segue",
    "umbraco",
    "clone",
    "classloader",
    "javafx-8",
    "box2d",
    "sh",
    "lazy-loading",
    "rails-activerecord",
    "eval",
    "cell",
    "exchange-server",
    "slideshow",
    "qt-creator",
    "google-oauth",
    "mongodb-query",
    "selection",
    "signal-processing",
    "powershell-v2.0",
    "primary-key",
    "big-o",
    "prototypejs",
    "log4net",
    "xss",
    "real-time",
    "jpa-2.0",
    "asp.net-identity",
    "response",
    "abstract-class",
    "unity",
    "bigdata",
    "distinct",
    "z-index",
    "nokogiri",
    "href",
    "carousel",
    "corona",
    "inputstream",
    "android-mediaplayer",
    "each",
    "firebug",
    "metaprogramming",
    "openmp",
    "overloading",
    "moq",
    "restkit",
    "reporting",
    "md5",
    "constants",
    "mootools",
    "gpu",
    "hudson",
    "limit",
    "antlr",
    "screenshot",
    "powerpoint",
    "openid",
    "leaflet",
    "vhdl",
    "accordion",
    "collision-detection",
    "innodb",
    "token",
    "session-variables",
    "windows-server-2008",
    "codeigniter-2",
    "markdown",
    "time-complexity",
    "javabeans",
    "command-line-arguments",
    "selector",
    "asp.net-web-api2",
    "parent-child",
    "bundler",
    "richtextbox",
    "vaadin",
    "transparency",
    "ssms",
    "linux-device-driver",
    "uigesturerecognizer",
    "mips",
    "master-pages",
    "ipc",
    "virtualenv",
    "xquery",
    "panel",
    "netty",
    "breeze",
    "accessibility",
    "scale"
  )
}