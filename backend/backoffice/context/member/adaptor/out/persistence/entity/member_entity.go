package entity

import (
	"github.com/seungyeop-lee/book-rental-shop/backend/backoffice/context/member/domain"
	"github.com/seungyeop-lee/book-rental-shop/backend/backoffice/context/member/vo"
	"gorm.io/gorm/schema"
)

type Member struct {
	Id          uint   `gorm:"id"`
	Name        string `gorm:"name"`
	PhoneNumber string `gorm:"phone_number"`
}

func NewMember(member domain.Member) *Member {
	return &Member{
		Id:          uint(member.Id),
		Name:        member.Name,
		PhoneNumber: member.PhoneNumber,
	}
}

var _ schema.Tabler = (*Member)(nil)

func (e *Member) TableName() string {
	return "member"
}

func (e *Member) MapToDomain() *domain.Member {
	return &domain.Member{
		Id:          vo.MemberId(e.Id),
		Name:        e.Name,
		PhoneNumber: e.PhoneNumber,
	}
}

func (e *Member) Update(member domain.Member) {
	e.Name = member.Name
	e.PhoneNumber = member.PhoneNumber
}
