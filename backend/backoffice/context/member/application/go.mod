module github.com/seungyeop-lee/book-rental-shop/backend/backoffice/context/member/application

go 1.19

require (
	github.com/golang/mock v1.6.0
	github.com/seungyeop-lee/book-rental-shop/backend/backoffice/context/member/domain v0.0.0-20230101203127-42b646b84a55
	github.com/seungyeop-lee/book-rental-shop/backend/backoffice/context/member/vo v0.0.0-20230101203127-42b646b84a55
)

replace (
	github.com/seungyeop-lee/book-rental-shop/backend/backoffice/context/member/domain => ../domain
	github.com/seungyeop-lee/book-rental-shop/backend/backoffice/context/member/vo => ../vo
)
