module github.com/seungyeop-lee/book-rental-shop/backend/backoffice/runner/member-manager

go 1.20

require (
	github.com/labstack/echo/v4 v4.10.2
	github.com/seungyeop-lee/book-rental-shop/backend/backoffice/context/member/adaptor v0.0.0-20230517151541-7b2995c79b64
	github.com/seungyeop-lee/book-rental-shop/backend/backoffice/context/member/application v0.0.0-20230517151541-7b2995c79b64
	gorm.io/driver/mysql v1.5.1
	gorm.io/gorm v1.25.1
)

require (
	github.com/go-sql-driver/mysql v1.7.1 // indirect
	github.com/jinzhu/inflection v1.0.0 // indirect
	github.com/jinzhu/now v1.1.5 // indirect
	github.com/labstack/gommon v0.4.0 // indirect
	github.com/mattn/go-colorable v0.1.13 // indirect
	github.com/mattn/go-isatty v0.0.18 // indirect
	github.com/seungyeop-lee/book-rental-shop/backend/backoffice/context/member/domain v0.0.0-20230517151541-7b2995c79b64 // indirect
	github.com/seungyeop-lee/book-rental-shop/backend/backoffice/context/member/vo v0.0.0-20230517151541-7b2995c79b64 // indirect
	github.com/valyala/bytebufferpool v1.0.0 // indirect
	github.com/valyala/fasttemplate v1.2.2 // indirect
	golang.org/x/crypto v0.9.0 // indirect
	golang.org/x/net v0.10.0 // indirect
	golang.org/x/sys v0.8.0 // indirect
	golang.org/x/text v0.9.0 // indirect
)

replace (
	github.com/seungyeop-lee/book-rental-shop/backend/backoffice/context/member/adaptor => ../../context/member/adaptor
	github.com/seungyeop-lee/book-rental-shop/backend/backoffice/context/member/application => ../../context/member/application
	github.com/seungyeop-lee/book-rental-shop/backend/backoffice/context/member/domain => ../../context/member/domain
	github.com/seungyeop-lee/book-rental-shop/backend/backoffice/context/member/vo => ../../context/member/vo
)
