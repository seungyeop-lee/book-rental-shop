module github.com/seungyeop-lee/book-rental-shop/backend/backoffice/context/member/adaptor

go 1.19

require (
	github.com/labstack/echo/v4 v4.10.0
	github.com/seungyeop-lee/book-rental-shop/backend/backoffice/context/member/application v0.0.0-20230101203127-42b646b84a55
	github.com/seungyeop-lee/book-rental-shop/backend/backoffice/context/member/domain v0.0.0-20230101203127-42b646b84a55
	github.com/seungyeop-lee/book-rental-shop/backend/backoffice/context/member/vo v0.0.0-20230101203127-42b646b84a55
	gorm.io/gorm v1.24.3
)

require (
	github.com/jinzhu/inflection v1.0.0 // indirect
	github.com/jinzhu/now v1.1.5 // indirect
	github.com/labstack/gommon v0.4.0 // indirect
	github.com/mattn/go-colorable v0.1.13 // indirect
	github.com/mattn/go-isatty v0.0.17 // indirect
	github.com/valyala/bytebufferpool v1.0.0 // indirect
	github.com/valyala/fasttemplate v1.2.2 // indirect
	golang.org/x/crypto v0.5.0 // indirect
	golang.org/x/net v0.5.0 // indirect
	golang.org/x/sys v0.4.0 // indirect
	golang.org/x/text v0.6.0 // indirect
)

replace (
	github.com/seungyeop-lee/book-rental-shop/backend/backoffice/context/member/application => ../application
	github.com/seungyeop-lee/book-rental-shop/backend/backoffice/context/member/domain => ../domain
	github.com/seungyeop-lee/book-rental-shop/backend/backoffice/context/member/vo => ../vo
)
