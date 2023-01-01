package domain

import "github.com/seungyeop-lee/book-rental-shop/backend/backoffice/context/member/vo"

type Member struct {
	Id          vo.MemberId
	Name        string
	PhoneNumber string
}

func NewMember(name string, phoneNumber string) *Member {
	return &Member{
		Id:          vo.EmptyMemberId,
		Name:        name,
		PhoneNumber: phoneNumber,
	}
}
